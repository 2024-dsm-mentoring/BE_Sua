package org.example.be_sua.domain.auth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RedisHash
public class RefreshToken {

    @Id
    private String accountId;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    public void updateToken(String refreshToken, Long ttl) {
        this.refreshToken = refreshToken;
        this.ttl = ttl;
    }
}
