package di_and_ioc.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FieldBean fieldBeanDefinition() {
        return new FieldBean();
    }

    @Bean
    public ItemBean itemBeanDefinition() {
        return new ItemBean();
    }

    @Bean
    public SetterBean setterBeanDefinition() {
        return new SetterBean();
    }

    @Bean
    public StoreBean storeDefinition() {
        StoreBean stBean = new StoreBean(itemBeanDefinition());
        stBean.setSetterBean(setterBeanDefinition());
        return stBean;
    }
}
