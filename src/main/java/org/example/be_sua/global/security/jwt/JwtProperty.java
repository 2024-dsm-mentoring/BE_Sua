package org.example.be_sua.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

    private final String secretKey;
    private final Long accessExp;
    private final Long refreshExp;
    private final String header;
    private final String prefix;
}
