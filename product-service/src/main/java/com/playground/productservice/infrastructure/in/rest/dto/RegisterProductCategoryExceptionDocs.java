package com.playground.productservice.infrastructure.in.rest.dto;

import com.playground.core.annotation.ExceptionDoc;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.swagger.SwaggerExampleExceptions;
import com.playground.productservice.domain.exception.DuplicateProductCategoryNameException;

@ExceptionDoc
public class RegisterProductCategoryExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError("기존의 상품 카테고리와 이름이 중복된 경우.")
    public BusinessException 이름_중복 = DuplicateProductCategoryNameException.EXCEPTION;

}
