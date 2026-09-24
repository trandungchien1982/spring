package demo.stream_lamda_map.services;

import demo.stream_lamda_map.repositories.UserDao;
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
