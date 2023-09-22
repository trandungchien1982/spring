package demo.runappcli.repositories;

import demo.runappcli.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface UserDao extends CrudRepository<User, Id> {
}
