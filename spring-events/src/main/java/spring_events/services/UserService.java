package spring_events.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring_events.entities.User;
import spring_events.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserDao userDao;

    public List<User> getListUsers() {
        List<User> users = new LinkedList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }

    public String addUser() {
        User user = new User();
        Long userId = System.currentTimeMillis();

        log.info("[UserService] :: Start adding new user: " + userId);
        user.setUserId(userId);
        user.setName("Name:" + userId);
        user.setAge((int)(userId % 100));
        user.setCreateDate(new Date());
        User saveUser = userDao.save(user);

        log.info("[UserService] :: Finish persist data of user: " + userId);
        return "[AddedUser] id=" + saveUser.getUserId() + ", name=" + saveUser.getName() + ", age=" + saveUser.getAge();
    }

    @Transactional (rollbackOn = SQLException.class)
    public String addUserWithError() throws Exception {
        String result = addUser();

        // Force throwing SQLException when executing this method :)
        if (true) {
            throw new SQLException("Force error in SQL of adding new user :) ");
        }
        return result;
    }
}
