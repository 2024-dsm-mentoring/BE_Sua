package org.example.be_sua.domain.auth.exception;

import org.example.be_sua.global.error.exception.CrudException;
import org.example.be_sua.global.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends CrudException {

    public static final RefreshTokenNotFoundException EXCEPTION = new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
