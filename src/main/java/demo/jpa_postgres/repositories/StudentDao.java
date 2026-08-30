package demo.jpa_postgres.repositories;

import demo.jpa_postgres.entities.Student;
import demo.jpa_postgres.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * DAO interface, we do not need inject @Bean
 * @author tdc
 */
public interface StudentDao extends JpaRepository<Student, Long> {

    // Fill all data inside a pager
    // The Pageable will include Sort feature
    public Page<Student> findAll(Pageable pageable);

}
