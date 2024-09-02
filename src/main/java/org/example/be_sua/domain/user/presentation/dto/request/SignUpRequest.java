package org.example.be_sua.domain.user.presentation.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @Email(message = "올바른 이메일을 입력해주세요")
    @Size(max = 65, message = "이메일은 64자 이하여야 합니다")
    private String email;

    @NotBlank
    @Size(min = 8, max = 20, message = "영어와 숫자를 포함한 8자~20자의 아이디를 입력해주세요")
    private String accountId;

    @NotBlank
    @Size(min = 8, max = 20, message = "영어와 숫자, 특수문자를 포함하는 8~20자의 비밀번호를 입력해주세요")
    private String password;
}
