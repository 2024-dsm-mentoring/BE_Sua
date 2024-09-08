package org.example.be_sua.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// CrudException이 발생했을때 상태코드와 메시지 반환
public enum ErrorCode {
    //server
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    //user
    USER_NOT_FOUND(404, "User Not Found"),
    ALREADY_USER(409, "Already User"),
    PASSWORD_MISMATCH(401, "Password Mismatch"),

    //jwt
    EXPIRED_JWT(401, "Expired JWT"),
    INVALID_JWT(401, "Invalid JWT"),
    INVALID_REFRESH_TOKEN(401, "Invalid Refresh Token"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found");


    private final int status;
    private final String message;
}