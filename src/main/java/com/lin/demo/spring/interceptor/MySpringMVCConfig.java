package com.lin.demo.spring.interceptor;

import com.lin.demo.spring.filter.ActionHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
/**
 * @author alex
 */
//@SpringBootConfiguration
public class MySpringMVCConfig extends WebMvcConfigurerAdapter {
 
    @Autowired
    private ActionHandlerInterceptor myInterceptor;
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/insuranceMch/**");
    }
}