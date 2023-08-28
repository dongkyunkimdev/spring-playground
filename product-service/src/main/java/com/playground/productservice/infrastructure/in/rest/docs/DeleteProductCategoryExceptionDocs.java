package com.playground.productservice.infrastructure.in.rest.docs;

import com.playground.core.annotation.ExceptionDoc;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.swagger.SwaggerExampleExceptions;
import com.playground.productservice.domain.exception.ProductCategoryNotFoundException;
import com.playground.productservice.domain.exception.ProductCategoryReferencedException;

@ExceptionDoc
public class DeleteProductCategoryExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError("상품 카테고리가 존재하지 않는 경우.")
    public BusinessException 상품_카테고리가_없음 = ProductCategoryNotFoundException.EXCEPTION;

    @ExplainError("상품 카테고리가 참조되고 있는 경우.")
    public BusinessException 상품_카테고리가_참조되고_있음 = ProductCategoryReferencedException.EXCEPTION;

}
