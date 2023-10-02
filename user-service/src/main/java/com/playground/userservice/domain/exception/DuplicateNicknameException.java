package com.playground.userservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class DuplicateNicknameException extends BusinessException {

    public static final BusinessException EXCEPTION = new DuplicateNicknameException();

    public DuplicateNicknameException() {
        super(UserErrorCode.NICKNAME_DUPLICATED);
    }

}
