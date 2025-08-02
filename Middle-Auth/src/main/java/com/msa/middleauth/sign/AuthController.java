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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    //private final AuthRepository authRepository;
    private final AuthenService authenService;

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> createAuthentication(
            @RequestBody @Valid Requestuser user
            ) throws Exception {
        AuthResponse response;
        if(authenService.createUser(user))
        {
            // 토큰 생성
            String token = authService.createAccessToken(user.getUser_id());

            // AuthResponse로 감싸서 반환
             response = new AuthResponse(
                    token,
                    "");

        }
        else{
            response = new AuthResponse(
                    null,
                    "존재하는 ID 입니다."
            );
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signUp/{user_id}")
    public ResponseEntity<ResultData<String>> getAuthorization(
        @PathVariable String user_id
    )
    {

    }

    @Data
    @AllArgsConstructor
    public static class AuthResponse {
        private String accessToken;
        private String accessResult;

    }
}
