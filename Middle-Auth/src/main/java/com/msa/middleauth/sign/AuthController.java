package com.msa.middleauth.sign;

import com.msa.middleauth.sign.cmmn.ResultData;
import com.msa.middleauth.sign.dto.Request.Requestuser;
import com.msa.middleauth.sign.entity.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthRepository authRepository;


    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> createAuthentication(
            @RequestBody @Valid Requestuser user
            ) throws Exception {
        User tempuser = new User(
                user.getUser_id(),
                user.getUser_pwd(),
                user.getUser_name(),
                user.getUser_phone()
        );
        authRepository.save(tempuser);

        // 토큰 생성
        String token = authService.createAccessToken(user.getUser_id());

        // AuthResponse로 감싸서 반환
        AuthResponse response = new AuthResponse(token);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/signUp")
//    public ResponseEntity<ResultData<String>>


    @GetMapping("/test")
    public String test() throws Exception {
        log.info("#####test#####");
        return "Test";
    }

    @Data
    @AllArgsConstructor
    public static class AuthResponse {
        private String accessToken;

    }
}
