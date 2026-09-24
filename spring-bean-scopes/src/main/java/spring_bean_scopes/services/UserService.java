package spring_bean_scopes.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Lookup;
import spring_bean_scopes.configs.PrototypeBeanObject;
import spring_bean_scopes.configs.RequestBeanObject;
import spring_bean_scopes.configs.SingleBeanObject;
import spring_bean_scopes.entities.User;
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
    SingleBeanObject singleBeanObject;

    @Autowired(required = false)
    RequestBeanObject requestBeanObject;

    @Autowired
    ObjectProvider<PrototypeBeanObject> prototypeBeanObjectsProvider;

    public List<User> getListUsers() {
        List<User> users = new LinkedList<>();

        // Make in each call, we will have a new instance of Bean Object
        PrototypeBeanObject prototypeBeanObject = prototypeBeanObjectsProvider.getObject();

        log.info("[UserService] The singleBeanObject instance: " + singleBeanObject);
        log.info("[UserService] The prototypeBeanObject instance: " + prototypeBeanObject + " - hashCode1: " + prototypeBeanObject.hashCode());
        log.info("[UserService] The requestBeanObject instance: " + requestBeanObject);
        return users;
    }

}
