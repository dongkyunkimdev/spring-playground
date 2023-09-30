package com.playground.userservice.infrastructure.in.rest.docs;

import com.playground.core.annotation.ExceptionDoc;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.swagger.SwaggerExampleExceptions;
import com.playground.userservice.domain.exception.PasswordMismatchException;
import com.playground.userservice.domain.exception.UserNotFoundException;

@ExceptionDoc
public class SignInExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError("유저가 존재하지 않는 경우.")
    public BusinessException 유저가_존재하지_않음 = UserNotFoundException.EXCEPTION;

    @ExplainError("비밀번호가 일치하지 않는 경우.")
    public BusinessException 비밀번호가_일치하지_않음 = PasswordMismatchException.EXCEPTION;

}
