package demo.jpa_postgres.services;

import demo.jpa_postgres.repositories.UserDao;
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
