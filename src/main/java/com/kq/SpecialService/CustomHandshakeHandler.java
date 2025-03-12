package com.kq.SpecialService;

import com.kq.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

// 自定义握手处理器
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    private final JwtTokenUtil jwtTokenUtil;

    public CustomHandshakeHandler(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected Principal determineUser(
            ServerHttpRequest request,
            WebSocketHandler handler,
            Map<String, Object> attributes
    ) {
        // 从请求参数或头中获取 Token
        String token = extractToken(request);
        if (token != null && jwtTokenUtil.validateToken(token)) {
            String userId = jwtTokenUtil.getUsernameFromToken(token).get("userId", String.class);
            return () -> userId; // 返回用户身份
        }
        return null; // 验证失败，拒绝连接
    }

    private String extractToken(ServerHttpRequest request) {
        // 从查询参数或头中提取 Token
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String tokenHeader = servletRequest.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                return tokenHeader.substring(7);
            }
        }
        return null;
    }
}

