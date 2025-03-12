package com.kq.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component//生成和验证JWT
public class JwtTokenUtil {
    private static long ttl = 60 * 1000 * 60;//token有效时间1小时
    private SecretKey key = Jwts.SIG.HS256.key().build();//创建密钥，用于签发和验证token
    public String generateToken(String userId) {
        Date expiryDate = new Date(new Date().getTime() + ttl);
        return Jwts.builder()
                .claim("userId", userId)
                .expiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getUsernameFromToken(String token) {//从token中获取用户信息
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            System.out.println(claims.get("userId"));
        }catch (JwtException ex){
            ex.printStackTrace();
        }
        return claims;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) throws Exception{//判断token是否过期
        Date expiryDate = getExpirationDateFromToken(token);
        return expiryDate.before(new Date());//比较时间
    }

    //获取过期时间
    private Date getExpirationDateFromToken(String token) throws IllegalArgumentException {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getExpiration();
        }catch (IllegalArgumentException exception){
            throw exception;
        }
    }
}

