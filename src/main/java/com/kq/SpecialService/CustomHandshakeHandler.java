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
        System.out.println("Handshake Request Headers: " + request.getHeaders());
        // 从请求参数或头中获取 Token
        String token = extractToken(request);
        System.out.println("Extracted Token: " + token);
        if (token != null) {
            // 验证 Token 是否有效
            boolean isValid = jwtTokenUtil.validateToken(token);
            System.out.println("Token 有效性: " + isValid);

            if (isValid) {
                //从 Token 中解析用户 ID
                String userId = jwtTokenUtil.getUsernameFromToken(token).get("userId", String.class);
                System.out.println("Token 解析出的用户 ID: " + userId);
                return () -> userId;
            }
        }
        System.out.println("Token 无效或未找到，拒绝连接");
        return null; // 验证失败，拒绝连接
    }

    private String extractToken(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

            // 从 URL 参数中提取 Token
            String token = servletRequest.getParameter("token");
            if (token != null) {
                return token;
            }

            // 保留原有从头提取逻辑
            String tokenHeader = servletRequest.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                return tokenHeader.substring(7);
            }
        }
        return null;
    }
}

