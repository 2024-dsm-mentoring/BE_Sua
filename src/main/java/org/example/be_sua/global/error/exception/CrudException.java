package org.example.be_sua.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.global.error.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public class CrudException extends RuntimeException{
    private final ErrorCode errorCode;
}
