package org.example.be_sua.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.user.presentation.dto.request.LoginRequest;
import org.example.be_sua.domain.user.presentation.dto.request.SignUpRequest;
import org.example.be_sua.domain.user.service.LoginService;
import org.example.be_sua.domain.user.service.SignupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final SignupService signupService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        signupService.signup(signUpRequest);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginRequest loginRequest) {
        loginService.login(loginRequest);
    }
}
