package com.playground.userservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class DuplicateUsernameException extends BusinessException {

    public static final BusinessException EXCEPTION = new DuplicateUsernameException();

    public DuplicateUsernameException() {
        super(UserErrorCode.USERNAME_DUPLICATED);
    }

}
