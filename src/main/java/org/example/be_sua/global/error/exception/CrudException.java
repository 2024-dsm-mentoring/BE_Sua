package org.example.be_sua.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.global.error.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
// 에러 상태와 관련된 코드와 메시지 등을 정의하는 클래스
public class CrudException extends RuntimeException{
    private final ErrorCode errorCode;
}
