package org.example.be_sua.global.exception;

import org.example.be_sua.global.error.exception.CrudException;
import org.example.be_sua.global.error.exception.ErrorCode;

public class ExpiredJwtException extends CrudException {

    public static final CrudException EXCEPTION = new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
