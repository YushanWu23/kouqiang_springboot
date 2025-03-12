/*
package com.kq.util;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    JwtTokenUtil jwtTokenUtil;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true; // OPTIONS 请求由 Spring Security 的 CORS 配置处理
        }

        // 获取请求的路径
        String path = request.getServletPath();

        // 如果路径是允许匿名访问的路径，直接放行
        List<String> anonymousPaths = Arrays.asList(
                "/uploads/**",
                "/ws/**",
                "/consultation/**",
                "/user/callModel",
                "/user/login",
                "/doctor/login",
                "/admin/login",
                "/user/register",
                "/user/sendRegisterEmailCode",
                "/user/passwordForget",
                "/user/sendForgetPasswordEmailCode",
                "/admin/passwordForget",
                "/admin/sendForgetPasswordEmailCode",
                "/doctor/passwordForget",
                "/doctor/sendForgetPasswordEmailCode"
        );

        // 使用 AntPathMatcher 匹配路径
        for (String allowedPath : anonymousPaths) {
            if (pathMatcher.match(allowedPath, path)) {
                return true;
            }
        }

        // 对其他路径进行 Token 验证
        String token = request.getHeader("Authorization");
        boolean result = jwtTokenUtil.validateToken(token); // 验证 Token 的有效性
        return result;
    }

    @Override//用于在请求处理之后进行后处理操作
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //调用父类的postHandle方法，执行默认的后处理操作
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//在请求完成之后进行清理操作
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}*/
