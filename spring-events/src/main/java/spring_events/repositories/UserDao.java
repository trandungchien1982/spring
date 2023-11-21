package spring_events.repositories;

import spring_events.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface UserDao extends CrudRepository<User, Id> {
}
