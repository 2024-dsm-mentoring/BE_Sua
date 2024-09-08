package org.example.be_sua.global.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
// 예외 발생 시 클라이언트에게 반환할 응답 형식을 정의
public class ErrorResponse {
    private final int status;
    private final String message;

    ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
