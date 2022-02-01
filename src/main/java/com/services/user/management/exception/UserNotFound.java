package com.services.user.management.exception;

import lombok.Getter;

@Getter
public class UserNotFound extends RuntimeException{
    private com.services.user.management.exception.ErrorCode errorCode;

    public UserNotFound(com.services.user.management.exception.ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public UserNotFound(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
