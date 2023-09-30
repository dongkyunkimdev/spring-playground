package com.playground.userservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class PasswordMismatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(UserErrorCode.PASSWORD_MISMATCH);
    }

}
