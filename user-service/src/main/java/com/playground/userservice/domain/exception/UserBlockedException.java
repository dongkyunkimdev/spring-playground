package com.playground.userservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class UserBlockedException extends BusinessException {

    public static final BusinessException EXCEPTION = new UserBlockedException();

    public UserBlockedException() {
        super(UserErrorCode.USER_BLOCKED);
    }

}
