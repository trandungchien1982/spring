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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class DynamicConfigsTests {
	static Logger log = LoggerFactory.getLogger(DynamicConfigsTests.class);

	@Autowired
	EnvsController envsController;

	@Autowired
	SystemEnvsController systemEnvsController;

	@Autowired
	ValuesController valuesController;

	@DynamicPropertySource
	static void settingDynamicSource(DynamicPropertyRegistry registry) {
		log.info(" >> CALL setting Dynamic Source ...");
		String currentDate = UUID.randomUUID().toString();
		registry.add("spring.datasource.url", () -> "DataSourceURL::" + currentDate);
		registry.add("spring.datasource.password", () -> "DataSourcePWD::" + currentDate);
		registry.add("spring.datasource.username",  () -> "DataSourceUSR::" + currentDate);
		registry.add("test.second.data", () -> "MyCustom::test.second.data - " + currentDate);
		registry.add("value04.system.env.path", () -> "MyCustom::value04.system.env.path - " + currentDate);
	}

	@Test
	void processDynamicTestCase01() {
		log.info(" >> In Dynamic TestCase 01 ...");
		showAllDataWithDynamicConfigs();
	}

	@Test
	void processDynamicTestCase02() {
		log.info(" >> In Dynamic TestCase 02 ...");
		showAllDataWithDynamicConfigs();
	}

	void showAllDataWithDynamicConfigs() {
		log.info(" >> Show all data with Dynamic Config some key/values in testing ...");

		List<String> lstEnvs = envsController.listEnvs();
		log.info("lstEnvs: " + lstEnvs);

		List<String> lstSystemEnvs = systemEnvsController.listSytemEnvsValues();
		log.info("lstSystemEnvs: " + lstSystemEnvs);

		List<String> lstValues = valuesController.listValues();
		log.info("lstValues: " + lstValues);
	}

}
