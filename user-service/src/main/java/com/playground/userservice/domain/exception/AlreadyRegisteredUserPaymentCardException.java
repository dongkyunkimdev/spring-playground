package com.playground.userservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class AlreadyRegisteredUserPaymentCardException extends BusinessException {

    public static final BusinessException EXCEPTION = new AlreadyRegisteredUserPaymentCardException();

    public AlreadyRegisteredUserPaymentCardException() {
        super(UserErrorCode.USER_PAYMENT_CARD_ALREADY_REGISTERED);
    }

}
