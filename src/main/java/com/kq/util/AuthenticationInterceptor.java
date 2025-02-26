package com.kq.util;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    JwtTokenUtil jwtTokenUtil;
    @Override//用于在请求到达Controller之前进行预处理。
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        //获取请求的Servlet路径
        String path = request.getServletPath();
        ArrayList<String> pathCan = new ArrayList<>();
        pathCan.add("/user/callModel");
        pathCan.add("/user/login");
        pathCan.add("/user/register");
        pathCan.add("/user/sendRegisterEmailCode");
        pathCan.add("/user/passwordForget");
        pathCan.add("/user/sendForgetPasswordEmailCode");
        /*pathCan.add("/business/getAll");
        pathCan.add("/business/getByOrderTypeId");*/
        pathCan.add("/orders/getOrdersByOrdersId");
        pathCan.add("/orders/createOrders");
        pathCan.add("/orders/getOrdersByUserId");
        pathCan.add("/orders/updateOrderState");
        if (pathCan.contains(path)){
            return true;
        }
        String token = request.getHeader("Authorization");
        boolean result = jwtTokenUtil.validateToken(token);//验证token的可效性
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
}
