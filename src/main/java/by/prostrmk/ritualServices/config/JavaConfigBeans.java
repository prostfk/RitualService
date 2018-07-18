package by.prostrmk.ritualServices.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfigBeans {

    @Bean
    public FilterRegistrationBean<AccessFilter> authFilter(){
        FilterRegistrationBean<AccessFilter> accessFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        accessFilterFilterRegistrationBean.setFilter(new AccessFilter());
        accessFilterFilterRegistrationBean.addUrlPatterns("/admin/*", "/admin");
        return accessFilterFilterRegistrationBean;
    }

}
