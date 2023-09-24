package demo.jpa_h2;

import demo.jpa_h2.repositories.UserDao;
import demo.jpa_h2.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@SpringBootApplication
public class JPAH2Application implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(JPAH2Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Data for users will be initialized in file data.sql
		log.info("[MAIN] Start running some initialize CLI ...");
		log.info("--------------------------------------------------------");
		log.info("[MAIN] Total of users: " + userService.countAllUsers());
		log.info("[MAIN] findAll() = " + userDao.findAll());
		log.info("[MAIN] findAll(Sort.by('name')) = " + userDao.findAll(Sort.by("name")));
		log.info("[MAIN] findByName('Paul') = " + userDao.findByName("Paul").get());
		log.info("[MAIN] findFirstByOrderByNameAsc() = " + userDao.findFirstByOrderByNameAsc());
		log.info("[MAIN] findTopByOrderByDescriptionDesc() = " + userDao.findTopByOrderByDescriptionDesc());
		log.info("[MAIN] queryFirst10ByName() = " + userDao.queryFirst10ByName("James", Pageable.ofSize(2)));
		log.info("[MAIN] findDataCustomSQL() = " + userDao.findDataCustomSQL().collect(Collectors.toList()));
	}
}
