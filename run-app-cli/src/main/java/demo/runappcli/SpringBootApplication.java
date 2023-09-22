package demo.runappcli;

import demo.runappcli.entities.User;
import demo.runappcli.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.List;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start running CLI for JavaApp ...");
		this.prepareData();
		List<User> listUsers = userService.getListUsers();
		log.warn("Total users: " + listUsers.size());
		listUsers.forEach(user -> {
			log.info(" -- User: " + user);
		});
		log.info(" >> We will stop the JavaApp now. Bye bye ...");
	}

	private void prepareData() {
		userService.addUser();
		userService.addUser();
		userService.addUser();
	}
}
