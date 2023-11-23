package spring_bean_scopes.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_bean_scopes.configs.PrototypeBeanObject;
import spring_bean_scopes.configs.RequestBeanObject;
import spring_bean_scopes.configs.SingleBeanObject;
import spring_bean_scopes.entities.User;
import spring_bean_scopes.services.UserService;

import java.util.List;

@RestController
public class UserController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Autowired
    SingleBeanObject singleBeanObject;

    @Autowired
    PrototypeBeanObject prototypeBeanObject;

    @Autowired(required = false)
    RequestBeanObject requestBeanObject;

    @GetMapping(path="/get-users")
    public List<User> listUsers() {
        log.info("--------------------------------------------------------------");
        log.info(" >> Try to create new request ... ");
        List<User> lst = userService.getListUsers();

        log.info("[UserController] The singleBeanObject instance: " + singleBeanObject);
        log.info("[UserController] The prototypeBeanObject instance: " + prototypeBeanObject);
        log.info("[UserController] The requestBeanObject instance: " + requestBeanObject);
        return lst;
    }
}
