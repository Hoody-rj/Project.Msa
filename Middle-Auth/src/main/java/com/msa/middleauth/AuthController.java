package com.msa.middleauth;

import com.msa.middleauth.cmmn.ResultData;
import com.msa.middleauth.dto.Request.Requestuser;
import com.msa.middleauth.entity.User;
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
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthRepository authRepository;


    @PostMapping("/signIn")
    public ResponseEntity<ResultData<AuthResponse>> createAuthentication(@RequestBody Requestuser user) throws Exception {
        User tempuser = new User(
                user.getUser_id(),
                user.getUser_pwd(),
                user.getUser_name(),
                user.getUser_phone()
        );

        authRepository.save(tempuser);
        
        return ResponseEntity.ok(ResultData.<AuthResponse>builder()
                .resultcheck(true)
                .resultdata(new AuthResponse(authService.createAccessToken(user.getUser_id())))
                .resultmessage("")
                .build());
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
