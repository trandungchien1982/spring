package demo.applyconfigs;

import demo.applyconfigs.controllers.EnvsController;
import demo.applyconfigs.controllers.SystemEnvsController;
import demo.applyconfigs.controllers.ValuesController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class ApplyConfigsApplicationTests {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	EnvsController envsController;

	@Autowired
	SystemEnvsController systemEnvsController;

	@Autowired
	ValuesController valuesController;

	@Test
	void contextLoads() {
		showAllData();
	}

	void showAllData() {
		log.info(" >> Show all data in Test Cases ...");

		List<String> lstEnvs = envsController.listEnvs();
		log.info("lstEnvs: " + lstEnvs);

		List<String> lstSystemEnvs = systemEnvsController.listSytemEnvsValues();
		log.info("lstSystemEnvs: " + lstSystemEnvs);

		List<String> lstValues = valuesController.listValues();
		log.info("lstValues: " + lstValues);
	}

}
