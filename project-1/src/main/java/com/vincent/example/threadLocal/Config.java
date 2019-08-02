package com.vincent.example.threadLocal;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<HttpFilter> httpFilter(){
        FilterRegistrationBean<HttpFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 设置filter
        filterRegistrationBean.setFilter(new HttpFilter());
        // 拦截规则
        filterRegistrationBean.addUrlPatterns("/threadLocal/*");
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
