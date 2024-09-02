package org.example.be_sua.domain.user.exception;

import org.example.be_sua.global.error.exception.CrudException;
import org.example.be_sua.global.error.exception.ErrorCode;

public class AlreadyUserException extends CrudException {
    public static final AlreadyUserException EXCEPTION = new AlreadyUserException();

    public AlreadyUserException() {
        super(ErrorCode.ALREADY_USER);
    }
}
