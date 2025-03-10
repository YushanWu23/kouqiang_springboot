package com.kq.SpecialService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF 保护
                .authorizeHttpRequests(authz -> authz
                        //.requestMatchers("/ws/**").authenticated() // 确保 WebSocket 端点需要认证
                        .requestMatchers("/ws/**").permitAll()  // 允许 WebSocket 连接
                        .requestMatchers("/consultation/**").authenticated()
                        .requestMatchers("/login", "/register").permitAll() // 允许访问登录和注册页面
                        .anyRequest().authenticated()) // 其他请求需要认证
                .formLogin(form -> form
                        .loginPage("/login").permitAll()) // 自定义登录页面
                .httpBasic(Customizer.withDefaults()); // 启用 HTTP Basic 认证

        return http.build();
    }
}
