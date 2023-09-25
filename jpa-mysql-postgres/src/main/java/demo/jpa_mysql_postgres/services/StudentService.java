package demo.jpa_mysql_postgres.services;

import demo.jpa_mysql_postgres.mysql.entities.Student;
import demo.jpa_mysql_postgres.mysql.repositories.StudentDao;
import demo.jpa_mysql_postgres.postgres.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    public long countAllStudent() {
        return studentDao.count();
    }
}
