package demo.jpa_h2;

import demo.jpa_h2.repositories.UserDao;
import demo.jpa_h2.services.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class JPAH2ApplicationTests {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	UserDao userDao;

	@Test
	void contextLoads() throws Exception {
		log.info("[TEST] Total of users: " + userService.countAllUsers());
		log.info("--------------------------------------------------------");
		log.info("[TEST] findAll() = " + userDao.findAll());
		log.info("[TEST] findAll(Sort.by('name')) = " + userDao.findAll(Sort.by("name")));
		log.info("[TEST] findByName('Paul') = " + userDao.findByName("Paul").get());
		log.info("[TEST] findFirstByOrderByNameAsc() = " + userDao.findFirstByOrderByNameAsc());
		log.info("[TEST] findTopByOrderByDescriptionDesc() = " + userDao.findTopByOrderByDescriptionDesc());
		log.info("[TEST] queryFirst10ByName() = " + userDao.queryFirst10ByName("James", Pageable.ofSize(2)));
		log.info("[TEST] findDataCustomSQL() = " + userDao.findDataCustomSQL().collect(Collectors.toList()));
	}

}
