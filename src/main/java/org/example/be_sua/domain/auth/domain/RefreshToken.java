package org.example.be_sua.domain.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "tbl_refresh_token")
@Entity
public class RefreshToken {

    @Id
    private String accountId;

    private String refreshToken;

    public void update(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
