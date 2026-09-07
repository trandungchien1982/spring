package demo.oom_gc_tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class OOM_GC_Application implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
        SpringApplication.run(OOM_GC_Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Data for users will be initialized in file data.sql
		log.info("[MAIN] Start App for testing OOM - GC ...");
		log.info("--------------------------------------------------------");
	}
}
