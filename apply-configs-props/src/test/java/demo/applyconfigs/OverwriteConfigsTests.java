package demo.applyconfigs;

import demo.applyconfigs.controllers.EnvsController;
import demo.applyconfigs.controllers.SystemEnvsController;
import demo.applyconfigs.controllers.ValuesController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
// Overwrite some specified key/values
@TestPropertySource(properties = {
		 "test.first.data=Overwrite of [TestFirstData]"
		,"value02.sub=Overwrite of [Value02 Sub + Content]"
})
// Overwrite using an external properties file
@TestPropertySource("classpath:overwrite-test.properties")
class OverwriteConfigsTests {
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	EnvsController envsController;

	@Autowired
	SystemEnvsController systemEnvsController;

	@Autowired
	ValuesController valuesController;

	@Test
	void contextLoads() {
		showAllDataWithOverwrite();
	}

	void showAllDataWithOverwrite() {
		log.info(" >> Overwrite some key/values in testing ...");

		List<String> lstEnvs = envsController.listEnvs();
		log.info("lstEnvs: " + lstEnvs);

		List<String> lstSystemEnvs = systemEnvsController.listSytemEnvsValues();
		log.info("lstSystemEnvs: " + lstSystemEnvs);

		List<String> lstValues = valuesController.listValues();
		log.info("lstValues: " + lstValues);
	}

}
