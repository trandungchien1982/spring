package demo.jpa_mysql_postgres.mysql.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import demo.jpa_mysql_postgres.mysql.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * DAO interface, we do not need inject @Bean
 * @author tdc
 */
public interface StudentDao extends CrudRepository<Student, Long> {
    /**
     * New function for finding item by `active` value (true/false <=> 1/0)
     */
    public Iterable<Student> findByActive(boolean active);

    // Fill all data inside a pager
    // The Pageable will include Sort feature
    public Page<Student> findAll(Pageable pageable);

    // Find all data with sorting
    public Iterable<Student> findAll(Sort sort);

    // Limit query results
    public Student findFirstByOrderByNameAsc();
    public Student findTopByOrderByDescriptionDesc();
    public Page<Student> queryFirst10ByName(String name, Pageable pageable);
    public Slice<Student> findTop3ByName(String name, Pageable pageable);
    public List<Student> findFirst10ByName(String name, Sort sort);
    public List<Student> findTop10ByName(String name, Pageable pageable);

    @Query("select u from Student u where id > 10") // HQL query
    public Stream<Student> findDataCustomSQL();

    public Stream<Student> readAllByNameNotNull();

    @Query("select u from User u where id > 10") // HQL query
    public Stream<Student> findDataCustomSQLAndPager(Pageable pageable);

    @Async
    public Future<Student> findByName(String name);
    @Async
    public CompletableFuture<Student> findOneByName(String name);
    @Async
    public ListenableFuture<Student> findOneByPassword(String password);

}