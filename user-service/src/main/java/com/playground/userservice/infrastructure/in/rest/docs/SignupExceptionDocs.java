package com.playground.userservice.infrastructure.in.rest.docs;

import com.playground.core.annotation.ExceptionDoc;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.swagger.SwaggerExampleExceptions;
import com.playground.userservice.domain.exception.DuplicateUsernameException;

@ExceptionDoc
public class SignupExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError("기존 유저와 username이 중복된 경우.")
    public BusinessException username이_중복됨 = DuplicateUsernameException.EXCEPTION;

}
