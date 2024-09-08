package org.example.be_sua.global.error;

import org.example.be_sua.global.error.exception.CrudException;
import org.example.be_sua.global.error.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 전역적으로 발생할 수 있는 예외를 잡아서 처리 하고, JSON 형식으로 클라에게 응답 제공
// 애플리케이션 전역에서 예외를 처리하고 사용자에게 응답을 제공
public class GlobalExceptionHandler {
    @ExceptionHandler(CrudException.class) // 특정 예외가 발생했을 때 호출될 메소드 지정
    public ResponseEntity<ErrorResponse> customExceptionHandling(CrudException e) {
        final ErrorCode errorCode = e.getErrorCode(); // 발생한 ErrorCode를 가져옴

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .build(),
                HttpStatus.valueOf(errorCode.getStatus()) // HTTP 상태 코드를 HTTPStatus 열거형 객체로 반환
        );
    }
}
