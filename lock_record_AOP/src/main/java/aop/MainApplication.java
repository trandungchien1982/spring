package aop;

import aop.controllers.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	EmployeeController employeeController;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("\n------------------------ LockRecord Application ... ");
		employeeController.addEmployee("EmpName01", "EmpId01");
		employeeController.updateEmployee();
		employeeController.removeEmployee("EmpId02");
		employeeController.exceptionCase("testEmpId");
	}
}
