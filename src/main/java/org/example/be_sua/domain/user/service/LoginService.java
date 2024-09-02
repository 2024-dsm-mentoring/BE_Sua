package org.example.be_sua.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.auth.exception.PasswordMisMatchException;
import org.example.be_sua.domain.auth.presentation.response.TokenResponse;
import org.example.be_sua.domain.user.domain.User;
import org.example.be_sua.domain.user.domain.repository.UserRepository;
import org.example.be_sua.domain.user.exception.UserNotFoundException;
import org.example.be_sua.domain.user.presentation.dto.request.LoginRequest;
import org.example.be_sua.global.security.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse signIn(LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        } //matches : 암호화 값을 비교

        String accessToken = jwtTokenProvider.generateAccessToken(request.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(request.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
