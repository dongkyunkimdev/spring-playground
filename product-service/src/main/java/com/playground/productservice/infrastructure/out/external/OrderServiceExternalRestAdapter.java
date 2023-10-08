package com.playground.productservice.infrastructure.out.external;

import com.playground.core.annotation.ExternalAdapter;
import com.playground.productservice.application.port.out.external.OrderServiceExternalPort;

@ExternalAdapter
public class OrderServiceExternalRestAdapter implements OrderServiceExternalPort {

    @Override
    public boolean isProductReferenced(Long productId) {
        // TODO: Order-Service 모듈 구현 이후 작성, isProductReferenced true로 ProductReferencedException가 발생하는 Integration 테스트 케이스도 작성
        return false;
    }

}
