package demo.jpa_postgres.controllers;

import demo.jpa_postgres.entities.Student;
import demo.jpa_postgres.entities.User;
import demo.jpa_postgres.repositories.StudentDao;
import demo.jpa_postgres.repositories.UserDao;
//import demo.jpa_postgres.services.ConcurrentUpdateStudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import java.util.stream.Stream;


@Controller
@RequestMapping(path="/student") // This means URL's start with /user (after Application path)
@Transactional // Transactional for CustomSQL
@Slf4j
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @PersistenceContext
  private EntityManager entityManager;

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

    private void saveStudentWithRetry(Student std, boolean updateValue, String name, Date now) {

      if (updateValue) {
        std.setName(name);
        std.setCreateDate(now);
      }
      studentDao.saveAndFlush(std);
    }

    private Sort getSort(String sortField, String sortType) {
        Sort sort = Sort.by(Order.asc(sortField));
        if ("desc".equalsIgnoreCase(sortType)) {
            sort = Sort.by(Order.desc(sortField));
        }

        return sort;
    }

}