package demo.jpa_mysql_postgres.controllers;

import demo.jpa_mysql_postgres.mysql.entities.Student;
import demo.jpa_mysql_postgres.mysql.repositories.StudentDao;
import demo.jpa_mysql_postgres.postgres.entities.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


@Controller
@RequestMapping(path="/user") // This means URL's start with /user (after Application path)
@Transactional // Transactional for CustomSQL
public class StudentController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private StudentDao studentDao;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllUsers() {
        // This returns a JSON or XML with the users
        return studentDao.findAll();
    }

    @GetMapping(path="/findbyid")
    public @ResponseBody Student findById(@RequestParam Long id) {
        // This returns a JSON or XML with the users
        return studentDao.findById(id).get();
    }

    @GetMapping(path="/find-by-active")
    public @ResponseBody Iterable<Student> findByActive(@RequestParam Boolean active) {
        // This returns a JSON or XML with the users
        return studentDao.findByActive(active);
    }

    @GetMapping(path="/find-all-by-page")
    public @ResponseBody Iterable<Student> findAllByPage(@RequestParam Integer page, @RequestParam Integer size) {
        List<Student> list = new LinkedList<>();
        studentDao.findAll(PageRequest.of(page, size)).iterator().forEachRemaining(list::add);

        return list;
    }

    @GetMapping(path="/find-all-by-page-sort")
    public @ResponseBody Iterable<Student> findAllByPageAndSort(@RequestParam Integer page, @RequestParam Integer size,
                                                             @RequestParam String sortField, @RequestParam String sortType) {
        List<Student> list = new LinkedList<>();
        Sort sort = getSort(sortField, sortType);
        Pageable pager = PageRequest.of(page, size, sort);

        studentDao.findAll(pager).iterator().forEachRemaining(list::add);

        return list;
    }

    @GetMapping(path="/insert")
    public @ResponseBody Iterable<Student> insert(@RequestParam int numOfUsers) {
        String userNamePrefix = "INS_" + RandomStringUtils.randomAlphanumeric(4);
        Date createDate = new Date();

        for (int i = 0; i < numOfUsers; i++) {
            Student newItem = new Student();
            newItem.setName(userNamePrefix + "_" + i);
            newItem.setPassword("Password INSERT NEW");
            newItem.setActive(false);
            newItem.setCreateDate(createDate);
            studentDao.save(newItem);
        }

        return studentDao.findAll();
    }

    @GetMapping(path="/update")
    public @ResponseBody Iterable<Student> update(@RequestParam long fromId, @RequestParam long toId,
                                               @RequestParam String newNamePrefix, @RequestParam int maxUpdateItems) {
        String newNamePX = newNamePrefix + "^^^" + RandomStringUtils.randomAlphanumeric(10);
        int updateItems = 0;
        long totalRecord = studentDao.count();
        int countLoop = 0;
        for (long id = fromId; id <= toId; id++) {

            countLoop++;
            if (countLoop > totalRecord) {
                log.info("EXIT because countLoop > totalRecord : " + countLoop + "/" + totalRecord);
                break;
            }

            if (updateItems > maxUpdateItems) {
                log.error("Reach to Max Update Items : " + maxUpdateItems);
                break;
            }

            Student updateUser = null;
            try {
                updateUser = studentDao.findById(id).get();
            } catch (Exception e) {
                log.error("Exception occur : " + e.getMessage());
            }

            if (updateUser == null) {
                log.info("User == NULL with id : " + id);
                continue;
            }

            log.info("Update User with id : " + id);
            updateUser.setName(newNamePX + "_" + id);
            studentDao.save(updateUser);

            updateItems++;
        }

        return studentDao.findAll();
    }

    @GetMapping(path="/sort")
    public @ResponseBody Iterable<Student> sort(@RequestParam String sortField, @RequestParam String sortType) {
        Sort sort = getSort(sortField, sortType);
        return studentDao.findAll(sort);
    }

    @GetMapping(path="/custom-sql")
    public @ResponseBody Iterable<Student> customSQL() {
        List<Student> list = new LinkedList<>();

        try (Stream<Student> stream = studentDao.findDataCustomSQL()) {
            stream.forEach(list::add);
        }

        return list;
    }

    @GetMapping(path="/custom-sql-pager")
    public @ResponseBody Iterable<Student> customSQLAndPager(@RequestParam Integer page, @RequestParam Integer size) {
        List<Student> list = new LinkedList<>();
        Pageable pager = PageRequest.of(page, size);

        try (Stream<Student> stream = studentDao.findDataCustomSQLAndPager(pager)) {
            stream.forEach(list::add);
        }

        return list;
    }

    @GetMapping(path="/findFirstByOrderByNameAsc")
    public @ResponseBody Student findFirstByOrderByNameAsc() {
        return studentDao.findFirstByOrderByNameAsc();
    }

    @GetMapping(path="/findTopByOrderByDescriptionDesc")
    public @ResponseBody Student findTopByOrderByDescriptionDesc() {
        return studentDao.findTopByOrderByDescriptionDesc();
    }

    @GetMapping(path="/queryFirst10ByName")
    public @ResponseBody Page<Student> queryFirst10ByName(
            @RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        Pageable pager = PageRequest.of(page, size);
        return studentDao.queryFirst10ByName(name, pager);
    }

    @GetMapping(path="/findTop3ByName")
    public @ResponseBody Slice<Student> findTop3ByName(
            @RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        Pageable pager = PageRequest.of(page, size);
        return studentDao.findTop3ByName(name, pager);
    }

    @GetMapping(path="/findFirst10ByName")
    public @ResponseBody List<Student> findFirst10ByName(
            @RequestParam String name, @RequestParam String sortField, @RequestParam String sortType) {
        Sort sort = getSort(sortField, sortType);
        return studentDao.findFirst10ByName(name, sort);
    }

    @GetMapping(path="/findTop10ByName")
    public @ResponseBody List<Student> findTop10ByName(
            @RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        Pageable pager = PageRequest.of(page, size);
        return studentDao.findTop10ByName(name, pager);
    }

    @GetMapping(path="/findByNameAsync")
    public @ResponseBody List<Student> findByNameAsync(@RequestParam String name) {
        try {
            Student user = studentDao.findByName(name).get();
            return Collections.singletonList(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(path="/findOneByNameAsync")
    public @ResponseBody Student findOneByNameAsync(@RequestParam String name) {
        try {
            Student user = studentDao.findOneByName(name).get();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(path="/findOneByPasswordAsync")
    public @ResponseBody Student findOneByPasswordAsync(@RequestParam String password) {
        try {
            Student user = studentDao.findOneByPassword(password).get();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Sort getSort(String sortField, String sortType) {
        Sort sort = Sort.by(Order.asc(sortField));
        if ("desc".equalsIgnoreCase(sortType)) {
            sort = Sort.by(Order.desc(sortField));
        }

        return sort;
    }

}