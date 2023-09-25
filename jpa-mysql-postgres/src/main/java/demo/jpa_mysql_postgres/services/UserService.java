package demo.jpa_mysql_postgres.services;

import demo.jpa_mysql_postgres.postgres.repositories.UserDao;
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
