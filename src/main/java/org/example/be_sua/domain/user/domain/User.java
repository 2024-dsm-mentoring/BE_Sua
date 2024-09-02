package org.example.be_sua.domain.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "tbl_user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "account_id", nullable = false, unique = true, length = 20)
    private String accountId;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Builder
    public User(String accountId, String password, String email) {
        this.accountId = accountId;
        this.password = password;
        this.email = email;
    }
}
