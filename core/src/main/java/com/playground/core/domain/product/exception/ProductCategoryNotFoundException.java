package com.playground.core.domain.product.exception;

import com.playground.core.common.exception.BusinessException;

public class ProductCategoryNotFoundException extends BusinessException {

    public ProductCategoryNotFoundException(Long id) {
        super("상품 카테고리가 존재하지 않습니다. :" + id);
    }

}
