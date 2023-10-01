package com.playground.userservice.infrastructure.in.rest.docs;

import com.playground.core.annotation.ExceptionDoc;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.swagger.SwaggerExampleExceptions;
import com.playground.userservice.domain.exception.UserNotFoundException;

@ExceptionDoc
public class GetUserExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError("유저가 존재하지 않는 경우.")
    public BusinessException 유저가_존재하지_않음 = UserNotFoundException.EXCEPTION;

}
