package com.playground.userservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class UserNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }

}
