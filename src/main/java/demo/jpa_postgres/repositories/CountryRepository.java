package demo.jpa_postgres.repositories;

import demo.jpa_postgres.entities.Country;
import demo.jpa_postgres.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO interface, we do not need inject @Bean
 * @author tdc
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

    // Fill all data inside a pager
    // The Pageable will include Sort feature
    public Page<Country> findAll(Pageable pageable);

}
