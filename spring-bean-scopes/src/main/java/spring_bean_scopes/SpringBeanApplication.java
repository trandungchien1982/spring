package spring_bean_scopes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import spring_bean_scopes.controllers.UserController;

@SpringBootApplication
public class SpringBeanApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserController userController;


	public static void main(String[] args) {
		SpringApplication.run(SpringBeanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("[main] Show the information of Spring Bean Scopes ... ");
		log.info("[main] Execute to 'http://localhost:8420/get-users' : ");
//		String requestGet = restTemplate.getForObject("http://localhost:8420/get-users", String.class);
//		log.info("[main] Result value: " + requestGet);
		//userController.listUsers();
		//userController.listUsers();
		//System.exit(0);
	}
}
