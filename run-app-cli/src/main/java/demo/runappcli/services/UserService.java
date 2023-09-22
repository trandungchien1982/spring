package demo.runappcli.services;

import demo.runappcli.entities.User;
import demo.runappcli.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

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
        user.setUserId(userId);
        user.setName("Name:" + userId);
        user.setAge((int)(userId % 100));
        User saveUser = userDao.save(user);
        return "[AddedUser] id=" + saveUser.getUserId() + ", name=" + saveUser.getName() + ", age=" + saveUser.getAge();
    }
}
