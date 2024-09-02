package org.example.be_sua.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.user.presentation.dto.request.SignUpRequest;
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

    @PostMapping("/signup")
    public void signup(@RequestBody SignUpRequest signUpRequest) {
        signupService.signup(signUpRequest);
    }


}
