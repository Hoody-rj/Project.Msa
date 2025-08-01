package com.msa.middleauth;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signIn")
    public ResponseEntity<?> createAuthentication(@RequestBody String user_id) throws Exception {
        return ResponseEntity.ok(new AuthResponse(authService.createAccessToken(user_id)));
    }



    @GetMapping("/auth/test")
    public String test() throws Exception {
        log.info("#####test#####");
        return "Test";
    }

    @Data
    @AllArgsConstructor
    static class AuthResponse {
        private String accessToken;

    }
}
