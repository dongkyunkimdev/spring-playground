package com.playground.userservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class UserInactiveException extends BusinessException {

    public static final BusinessException EXCEPTION = new UserInactiveException();

    public UserInactiveException() {
        super(UserErrorCode.USER_INACTIVE);
    }

}
