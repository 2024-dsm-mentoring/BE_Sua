package org.example.be_sua.global.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private final int status;
    private final String message;

    ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
