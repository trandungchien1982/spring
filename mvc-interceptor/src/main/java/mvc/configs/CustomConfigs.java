package mvc.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CustomConfigs implements WebMvcConfigurer {
    @Autowired
    CustomInterceptor customInterceptor;

    boolean initialAddInterceptors = true;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

        // TODO: Tạm thời có check inital để đảm bảo chỉ thêm interceptor đúng 1 lần ...
        if (initialAddInterceptors) {
            registry.addInterceptor(customInterceptor);
        }
        initialAddInterceptors = false;

    }
}