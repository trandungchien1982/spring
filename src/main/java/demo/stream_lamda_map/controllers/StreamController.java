package demo.stream_lamda_map.controllers;

import demo.stream_lamda_map.entities.User;
import demo.stream_lamda_map.repositories.UserDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Controller
@RequestMapping(path="/stream") // This means URL's start with /user (after Application path)
@Transactional // Transactional for CustomSQL
public class StreamController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserDao userDao;

    @GetMapping(path="/all")
    public @ResponseBody List<User> getAllUsers() {
        // This returns a JSON or XML with the users
       Iterable<User> users = userDao.findAll();
       Stream<User> stream = StreamSupport.stream(users.spliterator(), false);
       return stream
               .filter(u -> u.getId() >= 7 && u.getId() <= 17)
               .filter(u -> Optional.ofNullable(u.getActive()).orElse(false))
               .map(User::getId)
               .map(id -> userDao.findById(id).orElse(null))
               .filter(Objects::nonNull)
               .collect(Collectors.toList());
    }

  @GetMapping(path="/findbyid")
  public @ResponseBody String findById(@RequestParam Long id) {
    return userDao.findById(id)
            .map(user -> Optional.ofNullable(user.getName()).orElse("NotFound-Name"))
            .orElse("NotPresentUser");
  }

  @GetMapping(path="/test-optional-map")
  public @ResponseBody String optionalAndMap(@RequestParam Long id) {
    return userDao.findById(id)
            .map(User::getName).orElse("NotFound-All");
  }

  @GetMapping(path="/all-users")
  public @ResponseBody List<String> optionalListToList() {
    return StreamSupport.stream(userDao.findAll().spliterator(), false)
            .map(User::getName)
            .filter(s -> s.length() > 10)
            .collect(Collectors.toList());
  }
}