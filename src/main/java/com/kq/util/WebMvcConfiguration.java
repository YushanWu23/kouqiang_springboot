package com.kq.util;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    //是一个配置类，实现了 WebMvcConfigurer 接口
    @Resource
    AuthenticationInterceptor authenticationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {//添加拦截器
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**");//拦截所有的请求路径
    }
}
