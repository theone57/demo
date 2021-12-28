//package com.lin.demo.bean;
//
//import com.atguigu.gmall.cart.interceptors.LoginInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author lin
// * @version v1.0
// * @program gmall-1026
// * @description 配置SpringMVC，使过滤器生效
// * @date 2021-04-28 09:26
// */
//@Configuration
//public class WebMvcConfig  implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 拦截所有路径
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
//    }
//}
//
