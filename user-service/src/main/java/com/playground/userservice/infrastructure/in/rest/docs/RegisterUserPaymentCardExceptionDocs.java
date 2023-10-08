package com.playground.userservice.infrastructure.in.rest.docs;

import com.playground.core.annotation.ExceptionDoc;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.swagger.SwaggerExampleExceptions;
import com.playground.userservice.domain.exception.AlreadyRegisteredUserPaymentCardException;
import com.playground.userservice.domain.exception.UserNotFoundException;

@ExceptionDoc
public class RegisterUserPaymentCardExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError("이미 등록된 카드인 경우.")
    public BusinessException 카드가_이미_등록됨 = AlreadyRegisteredUserPaymentCardException.EXCEPTION;

    @ExplainError("유저가 존재하지 않는 경우.")
    public BusinessException 유저가_존재하지_않음 = UserNotFoundException.EXCEPTION;

    // TODO: 카드 명의자와 카드 정보가 일치하지 않는 경우.

    // TODO: 카드가 등록할 수 없는 상태인 경우.

}
