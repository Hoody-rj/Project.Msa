package com.msa.middleauth;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signIn")
    public ResponseEntity<?> createAuthentication(@RequestParam String user_id) throws Exception {
        return ResponseEntity.ok(new AuthResponse(authService.createAccessToken(user_id)));
    }

    @Data
    @AllArgsConstructor
    static class AuthResponse {
        private String accessToken;

    }
}
