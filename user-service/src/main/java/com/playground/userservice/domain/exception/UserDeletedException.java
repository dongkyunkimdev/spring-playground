package com.playground.userservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class UserDeletedException extends BusinessException {

    public static final BusinessException EXCEPTION = new UserDeletedException();

    public UserDeletedException() {
        super(UserErrorCode.USER_DELETED);
    }

}
