package com.msa.middleauth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    public AuthService(@Value("${service.jwt.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    private final SecretKey secretKey;

    public String createAccessToken(String user_id){
        return Jwts.builder()
                .claim("user_id", user_id)
                .claim("role", "ADMIN")
                .issuer(issuer) // 발급자 notice
                .issuedAt(new Date(System.currentTimeMillis()))// 발급 시간
                .expiration(new Date(System.currentTimeMillis() + accessExpiration)) // 발급시간에서 설정 값 중 지연 시간 더한 값
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
