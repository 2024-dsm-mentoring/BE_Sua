package org.example.be_sua.global.exception;

import org.example.be_sua.global.error.exception.CrudException;
import org.example.be_sua.global.error.exception.ErrorCode;

public class InvalidJwtException extends CrudException {

    public static final CrudException EXCEPTION = new InvalidJwtException();

    private InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}
