package org.example.be_sua.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.user.presentation.dto.request.LoginRequest;
import org.example.be_sua.domain.user.presentation.dto.request.SignUpRequest;
import org.example.be_sua.domain.user.service.LoginService;
import org.example.be_sua.domain.user.service.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final SignupService signupService;
    private final LoginService loginService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED) // 회원가입
    public void signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        signupService.signup(signUpRequest);
    }

    @PostMapping("/longin") // 로그인
    public void login(@RequestBody @Valid LoginRequest loginRequest) {
        loginService.login(loginRequest);
    }
}
