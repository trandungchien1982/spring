package demo.jpa_postgres.controllers;

import demo.jpa_postgres.entities.Country;
import demo.jpa_postgres.entities.Student;
import demo.jpa_postgres.entities.User;
import demo.jpa_postgres.repositories.CountryRepository;
import demo.jpa_postgres.repositories.StudentDao;
import demo.jpa_postgres.repositories.UserDao;
//import demo.jpa_postgres.services.ConcurrentUpdateStudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping(path="/student") // This means URL's start with /user (after Application path)
@Transactional // Transactional for CustomSQL
@Slf4j
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory emf;

//    @Autowired
//    private ConcurrentUpdateStudentService concurrentUpdateStudentService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllStudents() {
        // This returns a JSON or XML with the users
        return studentDao.findAll();
    }

    @GetMapping(path="/insert")
    public @ResponseBody Iterable<Student> insert(@RequestParam int numOfStudents) {
        String userNamePrefix = "INS_" + RandomStringUtils.randomAlphanumeric(4);
        Date createDate = new Date();

        for (int i = 0; i < numOfStudents; i++) {
            Student newItem = new Student();
            String randomIdx = RandomStringUtils.randomAlphabetic(5);
            newItem.setName(userNamePrefix + "_" + randomIdx);
            newItem.setEmail("Email_" + randomIdx);
            newItem.setDescription("Description_" + randomIdx);
            newItem.setCreateDate(createDate);
            studentDao.save(newItem);
        }

        return studentDao.findAll();
    }

    @GetMapping(path="/update")
    public @ResponseBody Student updateStudent() {
      // This returns a JSON or XML with the users
      Student std = studentDao.findById(1L).orElse(null);
      std.setCreateDate(new Date());
      return studentDao.save(std);
    }


    @GetMapping(path="/updateLock")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public @ResponseBody Student updateStudentLock(@RequestParam int waitSeconds) throws InterruptedException {
      log.info("Wait seconds: {}", waitSeconds);

      // This returns a JSON or XML with the users
      Student std = studentDao.findById(1L).orElse(null);
      log.info("Current std: {}", std);

      Thread.sleep(waitSeconds * 1000L);

      // The .refresh() to make sure the std is the latest instance data
      //  otherwise, the std will be the same previous call SQL query due to L1 cache ...
      //entityManager.refresh(std);
      std = studentDao.findById(1L).orElse(null);
      log.info("Current std (find AGAIN): {}", std);


      std.setName("New name value for waitSeconds: " + waitSeconds);
      std.setCreateDate(new Date());
      studentDao.saveAndFlush(std);

      log.info("Finish transaction of waitSeconds: {}", waitSeconds);
      return std;
    }

  @GetMapping(path="/testL1")
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public @ResponseBody Map<String,Object> testL1() throws InterruptedException {
    log.info("Try to testL1: ");

    List<Student> allStudents = studentDao.findAll();
    log.info(" -- Find All Student ... size = {}", allStudents.size());

    Set<Long> ids = allStudents.stream().map(Student::getId).collect(Collectors.toSet());
    log.info(" -- All ids: {}", ids);

    //  Browse all students and fetch again
    log.info(" -- Browse all students and fetch by each ids");
    for (int i = 0; i < allStudents.size(); i++) {
      Student itemStd  = allStudents.get(i);
      Student s = studentDao.findById(itemStd.getId()).orElse(null);
      if (itemStd == s) {
        log.info("itemStd == s, L1 cache is working properly ...");

      } else {
        log.info("NOT EQUALS at index: {}, itemStd: {}, s: {}", i, itemStd, s);
      }
    }

    return Map.of("id", ids, "itemData", allStudents);
  }

  @GetMapping(path="/testL2")
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public @ResponseBody Map<String,Object> testL2() throws InterruptedException {
    log.info("Try to testL2: ");

    List<Country> allCountries = countryRepository.findAll();

    //  Browse all students and fetch again
    log.info(" -- Browse all countries and fetch by each ids");
    for (int i = 1; i <= 5; i++) {
      //Country itemCt  = allCountries.get(i);
      Country s = countryRepository.findById((long)i).orElse(null);
    }

    printStats();


    return Map.of("itemData", "Test Lvl2");
  }


  public void printStats() {

    SessionFactory sessionFactory =
            emf.unwrap(SessionFactory.class);

    Statistics statistics =
            sessionFactory.getStatistics();

    System.out.println(
            "L2 Hit = " +
                    statistics.getSecondLevelCacheHitCount()
    );

    System.out.println(
            "L2 Miss = " +
                    statistics.getSecondLevelCacheMissCount()
    );

    System.out.println(
            "L2 Put = " +
                    statistics.getSecondLevelCachePutCount()
    );
  }

    private Sort getSort(String sortField, String sortType) {
        Sort sort = Sort.by(Order.asc(sortField));
        if ("desc".equalsIgnoreCase(sortType)) {
            sort = Sort.by(Order.desc(sortField));
        }

        return sort;
    }

}