package org.example.be_sua.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.auth.domain.RefreshToken;
import org.example.be_sua.domain.auth.domain.repository.RefreshTokenRepository;
import org.example.be_sua.domain.auth.exception.InvalidRefreshTokenException;
import org.example.be_sua.domain.auth.exception.RefreshTokenNotFoundException;
import org.example.be_sua.domain.auth.presentation.response.TokenResponse;
import org.example.be_sua.global.security.jwt.JwtProperty;
import org.example.be_sua.global.security.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperty jwtProperty;

    @Transactional
    public TokenResponse userReissue(String refreshToken) {
        String parseToken = jwtTokenProvider.parseToken(refreshToken);
        if (parseToken == null) {
            throw InvalidRefreshTokenException.EXCEPTION;
        }

        RefreshToken redisRefreshtoken = refreshTokenRepository.findByToken(parseToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(redisRefreshtoken.getAccountId());
        redisRefreshtoken.updateToken(newRefreshToken, jwtProperty.getRefreshExp());

        String newAccesstoken = jwtTokenProvider.generateAccessToken(redisRefreshtoken.getAccountId());

        return TokenResponse.builder()
                .accessToken(newAccesstoken)
                .refreshToken(parseToken)
                .build();
    }
}
