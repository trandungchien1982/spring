package demo.jpa_h2.services;

import demo.jpa_h2.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public long countAllUsers() {
        return userDao.count();
    }
}
