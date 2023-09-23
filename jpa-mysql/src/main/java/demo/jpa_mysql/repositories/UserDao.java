package demo.jpa_mysql.repositories;

import demo.jpa_mysql.entities.User;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

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
public interface UserDao extends CrudRepository<User, Long> {
    /**
     * New function for finding item by `active` value (true/false <=> 1/0)
     */
    public Iterable<User> findByActive(boolean active);

    // Fill all data inside a pager
    // The Pageable will include Sort feature
    public Page<User> findAll(Pageable pageable);

    // Find all data with sorting
    public Iterable<User> findAll(Sort sort);

    // Limit query results
    public User findFirstByOrderByNameAsc();
    public User findTopByOrderByDescriptionDesc();
    public Page<User> queryFirst10ByName(String name, Pageable pageable);
    public Slice<User> findTop3ByName(String name, Pageable pageable);
    public List<User> findFirst10ByName(String name, Sort sort);
    public List<User> findTop10ByName(String name, Pageable pageable);

    @Query("select u from User u where id > 10") // HQL query
    public Stream<User> findDataCustomSQL();

    public Stream<User> readAllByNameNotNull();

    @Query("select u from User u where id > 10") // HQL query
    public Stream<User> findDataCustomSQLAndPager(Pageable pageable);

    @Async
    public Future<User> findByName(String name);
    @Async
    public CompletableFuture<User> findOneByName(String name);
    @Async
    public ListenableFuture<User> findOneByPassword(String password);

}
