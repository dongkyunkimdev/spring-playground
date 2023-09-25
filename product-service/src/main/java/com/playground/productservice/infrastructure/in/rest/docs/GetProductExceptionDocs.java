package com.playground.productservice.infrastructure.in.rest.docs;

import com.playground.core.annotation.ExceptionDoc;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.swagger.SwaggerExampleExceptions;
import com.playground.productservice.domain.exception.ProductNotFoundException;

@ExceptionDoc
public class GetProductExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError("상품이 존재하지 않는 경우.")
    public BusinessException 상품이_없음 = ProductNotFoundException.EXCEPTION;

}
