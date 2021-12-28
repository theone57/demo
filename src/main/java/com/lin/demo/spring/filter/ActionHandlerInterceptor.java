package com.lin.demo.spring.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class ActionHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {

//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request1 = attributes.getRequest();
//            String request = ServletUtil.getBody(request1);
//            System.out.println("request = " + request);

            // 读取inputSteam 信息(copy方式)
//            String bodyStr = IOUtils.toString(httpServletRequest.getInputStream(), StandardCharsets.UTF_8);
            System.out.println("拦截器。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}