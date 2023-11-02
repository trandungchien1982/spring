package demo.applyconfigs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplyConfigsApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(ApplyConfigsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start running some initialize CLI ...");
	}
}
