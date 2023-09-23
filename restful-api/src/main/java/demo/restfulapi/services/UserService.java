package demo.restfulapi.services;

import demo.restfulapi.entities.User;
import demo.restfulapi.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
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

    public List<User> getListUsers(Integer limit) {
        List<User> users = new LinkedList<>();
        userDao.findAll().forEach(user -> {
            if (users.size() < limit) {
                users.add(user);
            }
        });
        return users;
    }

    public User getUser(Long userId) {
        return userDao.findById(userId).orElse(null);
    }

    public String addUser() {
        User user = new User();
        Long userId = System.currentTimeMillis();
        user.setUserId(userId);
        user.setName("Name:" + userId);
        user.setAge((int)(userId % 100));
        User saveUser = userDao.save(user);
        return "[AddedUser] id=" + saveUser.getUserId() + ", name=" + saveUser.getName() + ", age=" + saveUser.getAge()
                + " ------ Total of users: " + countAllUsers();
    }

    public String addCustomUser(Long userId, String name, int age) {
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setAge(age);
        User saveUser = userDao.save(user);
        return "[AddedCustomUser] id=" + saveUser.getUserId() + ", name=" + saveUser.getName() + ", age=" + saveUser.getAge()
                + " ------ Total of users: " + countAllUsers();
    }

    public String updateUser(Long userId, String updateName, int updateAge) {
        User user = getUser(userId);
        if (user == null) {
            return "NotFound for userId: " + userId;
        }
        user.setName(updateName);
        user.setAge(updateAge);
        userDao.save(user);
        return "[UpdatedUser] userId=" + userId + ", name=" + updateName + ", age=" + updateAge
                + " ------ Total of users: " + countAllUsers();
    }

    public String deleteUser(Long userId) {
        User user = getUser(userId);
        if (user == null) {
            return "NotFound for userId: " + userId;
        }
        userDao.deleteById(userId);
        return "[DeleteUser] id=" + userId
                + " ------ Total of users: " + countAllUsers();
    }

    public long countAllUsers() {
        return userDao.count();
    }
}
