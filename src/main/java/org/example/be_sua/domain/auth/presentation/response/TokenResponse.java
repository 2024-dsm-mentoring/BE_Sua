package org.example.be_sua.domain.auth.presentation.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

    private final String accessToken;
    private final String refreshToken;
}
