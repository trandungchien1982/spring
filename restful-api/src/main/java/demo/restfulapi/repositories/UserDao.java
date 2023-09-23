package demo.restfulapi.repositories;

import demo.restfulapi.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface UserDao extends CrudRepository<User, Long> {
}
