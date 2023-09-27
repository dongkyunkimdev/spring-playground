package com.playground.productservice.infrastructure.in.rest.docs;

import com.playground.core.annotation.ExceptionDoc;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.swagger.SwaggerExampleExceptions;
import com.playground.productservice.domain.exception.ProductReferencedException;

@ExceptionDoc
public class DeleteProductExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError("상품이 참조되고 있는 경우.")
    public BusinessException 상품이_참조되고_있음 = ProductReferencedException.EXCEPTION;

}
