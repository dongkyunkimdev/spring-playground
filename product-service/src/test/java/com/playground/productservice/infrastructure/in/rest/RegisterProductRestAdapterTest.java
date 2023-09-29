package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.productservice.domain.exception.ProductErrorCode;
import com.playground.productservice.infrastructure.in.rest.dto.CommonProductCategoryResponse;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductResponse;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.playground.productservice.support.AssertUtil.assertErrorResponse;
import static com.playground.productservice.support.AssertUtil.assertSuccessResponse;
import static com.playground.productservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class RegisterProductRestAdapterTest extends ControllerTest {

    @Test
    void 상품_등록_성공() throws Exception {
        // given
        final RegisterProductRequest requestDto = RegisterProductRequest.builder()
            .name("Black T-Shirts")
            .stock(100L)
            .price(BigDecimal.valueOf(1000))
            .productCategoryId(1L)
            .build();

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/products")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isCreated());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.CREATED);

        RegisterProductResponse productResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productResponse.productId()).isNotNull();
        assertThat(productResponse.name()).isEqualTo(requestDto.name());
        assertThat(productResponse.stock()).isEqualTo(requestDto.stock());
        assertThat(productResponse.price()).isEqualTo(requestDto.price());

        CommonProductCategoryResponse productCategoryResponse = productResponse.productCategory();
        assertThat(productCategoryResponse.productCategoryId()).isEqualTo(requestDto.productCategoryId());
        assertThat(productResponse.productCategory().name()).isEqualTo("Clothing");
    }

    @Test
    void 상품_등록_실패_카테고리가_존재하지_않음() throws Exception {
        // given
        final RegisterProductRequest requestDto = RegisterProductRequest.builder()
            .name("Black T-Shirts")
            .stock(100L)
            .price(BigDecimal.valueOf(1000))
            .productCategoryId(1234L)
            .build();

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/products")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNotFound());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND);
    }

}
