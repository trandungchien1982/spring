package demo.restfulapi;

import demo.restfulapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulAPIApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(RestfulAPIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Data for users will be initialized in file data.sql
		log.info("Start running some initialize CLI ...");
	}
}
