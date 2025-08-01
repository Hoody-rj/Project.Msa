package com.msa.frontgateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Slf4j
public class LocalJwtAuthenticationFilter implements GlobalFilter {
    @Value("${service.jwt.secret-key}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        String passpath = exchange.getRequest().getURI().getPath();



        //SignIn 즉 회원가입일 경우 패스
        if (passpath.endsWith("/auth/signIn")) {
            return chain.filter(exchange);
        }
        //gateway에서 만료 일자만 확인하고 패스
        String token = extractToken(exchange);

        if (token == null || !validateToken(token)) { // validatetoken 함수의 결과 값으로 확인
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange){
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token){
        try{
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token);

            if (claims.getBody().getExpiration() == null || claims.getBody().getExpiration().before(new Date())) {
                log.warn("토큰 만료됨");
                return false;
            }

            log.info("#####payload : " + claims.getPayload().toString());
            log.info("#####date : " + claims.getBody().getIssuedAt() + " ~ " + claims.getBody().getExpiration());
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
