package demo.jpa_mysql_postgres.controllers;

import demo.jpa_mysql_postgres.postgres.entities.User;
import demo.jpa_mysql_postgres.postgres.repositories.UserDao;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/user") // This means URL's start with /user (after Application path)
@Transactional // Transactional for CustomSQL
public class UserController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserDao userDao;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userDao.findAll();
    }

    @GetMapping(path="/findbyid")
    public @ResponseBody User findById(@RequestParam Long id) {
        // This returns a JSON or XML with the users
        return userDao.findById(id).get();
    }

    @GetMapping(path="/find-by-active")
    public @ResponseBody Iterable<User> findByActive(@RequestParam Boolean active) {
        // This returns a JSON or XML with the users
        return userDao.findByActive(active);
    }

    @GetMapping(path="/find-all-by-page")
    public @ResponseBody Iterable<User> findAllByPage(@RequestParam Integer page, @RequestParam Integer size) {
        List<User> list = new LinkedList<>();
        userDao.findAll(PageRequest.of(page, size)).iterator().forEachRemaining(list::add);

        return list;
    }

    @GetMapping(path="/find-all-by-page-sort")
    public @ResponseBody Iterable<User> findAllByPageAndSort(@RequestParam Integer page, @RequestParam Integer size,
                                                             @RequestParam String sortField, @RequestParam String sortType) {
        List<User> list = new LinkedList<>();
        Sort sort = getSort(sortField, sortType);
        Pageable pager = PageRequest.of(page, size, sort);

        userDao.findAll(pager).iterator().forEachRemaining(list::add);

        return list;
    }

    @GetMapping(path="/insert")
    public @ResponseBody Iterable<User> insert(@RequestParam int numOfUsers) {
        String userNamePrefix = "INS_" + RandomStringUtils.randomAlphanumeric(4);
        Date createDate = new Date();

        for (int i = 0; i < numOfUsers; i++) {
            User newItem = new User();
            newItem.setName(userNamePrefix + "_" + i);
            newItem.setPassword("Password INSERT NEW");
            newItem.setActive(false);
            newItem.setCreateDate(createDate);
            userDao.save(newItem);
        }

        return userDao.findAll();
    }

    @GetMapping(path="/update")
    public @ResponseBody Iterable<User> update(@RequestParam long fromId, @RequestParam long toId,
                                               @RequestParam String newNamePrefix, @RequestParam int maxUpdateItems) {
        String newNamePX = newNamePrefix + "^^^" + RandomStringUtils.randomAlphanumeric(10);
        int updateItems = 0;
        long totalRecord = userDao.count();
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

            User updateUser = null;
            try {
                updateUser = userDao.findById(id).get();
            } catch (Exception e) {
                log.error("Exception occur : " + e.getMessage());
            }

            if (updateUser == null) {
                log.info("User == NULL with id : " + id);
                continue;
            }

            log.info("Update User with id : " + id);
            updateUser.setName(newNamePX + "_" + id);
            userDao.save(updateUser);

            updateItems++;
        }

        return userDao.findAll();
    }

    @GetMapping(path="/sort")
    public @ResponseBody Iterable<User> sort(@RequestParam String sortField, @RequestParam String sortType) {
        Sort sort = getSort(sortField, sortType);
        return userDao.findAll(sort);
    }

    @GetMapping(path="/custom-sql")
    public @ResponseBody Iterable<User> customSQL() {
        List<User> list = new LinkedList<>();

        try (Stream<User> stream = userDao.findDataCustomSQL()) {
            stream.forEach(list::add);
        }

        return list;
    }

    @GetMapping(path="/custom-sql-pager")
    public @ResponseBody Iterable<User> customSQLAndPager(@RequestParam Integer page, @RequestParam Integer size) {
        List<User> list = new LinkedList<>();
        Pageable pager = PageRequest.of(page, size);

        try (Stream<User> stream = userDao.findDataCustomSQLAndPager(pager)) {
            stream.forEach(list::add);
        }

        return list;
    }

    @GetMapping(path="/findFirstByOrderByNameAsc")
    public @ResponseBody User findFirstByOrderByNameAsc() {
        return userDao.findFirstByOrderByNameAsc();
    }

    @GetMapping(path="/findTopByOrderByDescriptionDesc")
    public @ResponseBody User findTopByOrderByDescriptionDesc() {
        return userDao.findTopByOrderByDescriptionDesc();
    }

    @GetMapping(path="/queryFirst10ByName")
    public @ResponseBody Page<User> queryFirst10ByName(
            @RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        Pageable pager = PageRequest.of(page, size);
        return userDao.queryFirst10ByName(name, pager);
    }

    @GetMapping(path="/findTop3ByName")
    public @ResponseBody Slice<User> findTop3ByName(
            @RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        Pageable pager = PageRequest.of(page, size);
        return userDao.findTop3ByName(name, pager);
    }

    @GetMapping(path="/findFirst10ByName")
    public @ResponseBody List<User> findFirst10ByName(
            @RequestParam String name, @RequestParam String sortField, @RequestParam String sortType) {
        Sort sort = getSort(sortField, sortType);
        return userDao.findFirst10ByName(name, sort);
    }

    @GetMapping(path="/findTop10ByName")
    public @ResponseBody List<User> findTop10ByName(
            @RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        Pageable pager = PageRequest.of(page, size);
        return userDao.findTop10ByName(name, pager);
    }

    @GetMapping(path="/findByNameAsync")
    public @ResponseBody List<User> findByNameAsync(@RequestParam String name) {
        try {
            User user = userDao.findByName(name).get();
            return Collections.singletonList(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(path="/findOneByNameAsync")
    public @ResponseBody User findOneByNameAsync(@RequestParam String name) {
        try {
            User user = userDao.findOneByName(name).get();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(path="/findOneByPasswordAsync")
    public @ResponseBody User findOneByPasswordAsync(@RequestParam String password) {
        try {
            User user = userDao.findOneByPassword(password).get();
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