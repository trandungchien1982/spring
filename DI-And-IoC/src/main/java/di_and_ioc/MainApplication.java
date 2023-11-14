package di_and_ioc;

import di_and_ioc.di.DIComponent;
import di_and_ioc.traditional.TraditionalEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	@Autowired
	DIComponent diComponent;

	@Autowired
	ApplicationContext applicationContext;

	Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("\n------------------------ Traditional impl ... ");
		TraditionalEx tranditionalEx = new TraditionalEx();
		tranditionalEx.traditionalImpl();

		log.info("\n------------------------ IoC / DI impl ... ");
		diComponent.diEx();

		log.info("\n------------------------ ApplicationContext object ...");
		log.info("[AppCtx] applicationName: " + applicationContext.getApplicationName());
		log.info("[AppCtx] displayName: " + applicationContext.getDisplayName());
		log.info("[AppCtx] id: " + applicationContext.getId());
		log.info("[AppCtx] beanDefinitionCount: " + applicationContext.getBeanDefinitionCount());
		log.info("[AppCtx] beanDefinitionNames: " + applicationContext.getBeanDefinitionNames());
		for (String beanName: applicationContext.getBeanDefinitionNames()) {
			log.info("[AppCtx]  --- beanName : " + beanName);
		}
	}
}
