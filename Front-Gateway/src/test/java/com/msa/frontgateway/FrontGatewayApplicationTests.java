package com.msa.frontgateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

@SpringBootTest
class FrontGatewayApplicationTests {

    @Test
    void contextLoads() {
        String KeyCode = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoianN3MDIzc3MiLCJyb2xlIjoiQURNSU4iLCJpc3MiOiJtaWRkbGUtYXV0aCIsImlhdCI6MTc1NDEwMTM2MiwiZXhwIjoxNzU0MTA0OTYyfQ.QNb9gQwCm1BeV1rnw-fau0tlsy2VNuRQBeAaspa8LFS3A-sTHiQhzgTbFrcaXDhXHzG_lugieR9ZFz1NYFCuag";
        String secretKey = "401b09eab3c013d4ca54922bb802bec8fd5318192b0a75f201d8b3727429080fb337591abd3e44453b954555b7a0812e1081c39b740293f765eae731f5a65ed1";
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        Jws<Claims> claims = Jwts.parser()
                .verifyWith(key)
                .build().parseSignedClaims(KeyCode);

    }

}
