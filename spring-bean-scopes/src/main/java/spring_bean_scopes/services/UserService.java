package spring_bean_scopes.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring_bean_scopes.configs.PrototypeBeanObject;
import spring_bean_scopes.configs.RequestBeanObject;
import spring_bean_scopes.configs.SingleBeanObject;
import spring_bean_scopes.entities.User;
import spring_bean_scopes.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserDao userDao;

    @Autowired
    SingleBeanObject singleBeanObject;

    @Autowired
    PrototypeBeanObject prototypeBeanObject;

    @Autowired(required = false)
    RequestBeanObject requestBeanObject;

    public List<User> getListUsers() {
        List<User> users = new LinkedList<>();

        log.info("[UserService] The singleBeanObject instance: " + singleBeanObject);
        log.info("[UserService] The prototypeBeanObject instance: " + prototypeBeanObject);
        log.info("[UserService] The requestBeanObject instance: " + requestBeanObject);
        return users;
    }

}
