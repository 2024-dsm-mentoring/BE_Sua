package org.example.be_sua.domain.auth.exception;

import org.example.be_sua.global.error.exception.CrudException;
import org.example.be_sua.global.error.exception.ErrorCode;

public class InvalidRefreshTokenException extends CrudException {

    public static final InvalidRefreshTokenException EXCEPTION = new InvalidRefreshTokenException();

    private InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
