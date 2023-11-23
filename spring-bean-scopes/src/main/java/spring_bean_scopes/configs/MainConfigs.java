package spring_bean_scopes.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class MainConfigs {

    Logger log = LoggerFactory.getLogger(getClass());

    int singleBeanIdx = 0;
    @Bean
    SingleBeanObject singleBeanObject() {
        log.info(" >> Try to create new instance of SingleBeanObject: " + singleBeanIdx);

        SingleBeanObject bean = new SingleBeanObject();
        bean.setBeanId("SingleBeanID::" + singleBeanIdx);
        bean.setBeanId("SingleBeanValue::" + singleBeanIdx);
        singleBeanIdx++;

        return bean;
    }

    int prototypeBeanIdx = 0;
    @Bean
    @Scope(value="prototype")
    PrototypeBeanObject prototypeBeanObject() {
        log.info(" >> Try to create new instance of PrototypeBeanObject: " + prototypeBeanIdx);

        PrototypeBeanObject bean = new PrototypeBeanObject();
        bean.setBeanId("PrototypeBeanID::" + prototypeBeanIdx);
        bean.setBeanId("PrototypeBeanID::" + prototypeBeanIdx);
        prototypeBeanIdx++;

        return bean;
    }

    int requestBeanIdx = 0;
    @Bean
    @RequestScope
    RequestBeanObject requestBeanObject() {
        log.info(" >> Try to create new instance of RequestBeanObject: " + requestBeanIdx);

        RequestBeanObject bean = new RequestBeanObject();
        bean.setBeanId("RequestBeanObject::" + requestBeanIdx);
        bean.setBeanId("RequestBeanObject::" + requestBeanIdx);
        requestBeanIdx++;

        return bean;
    }
}
