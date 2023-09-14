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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        MockHttpServletRequestBuilder requestBuilder = post("/v1/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isCreated());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(201);

        RegisterProductResponse productResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productResponse.getProductId()).isNotNull();
        assertThat(productResponse.getName()).isEqualTo(requestDto.getName());
        assertThat(productResponse.getStock()).isEqualTo(requestDto.getStock());
        assertThat(productResponse.getPrice()).isEqualTo(requestDto.getPrice());

        CommonProductCategoryResponse productCategoryResponse = productResponse.getProductCategory();
        assertThat(productCategoryResponse.getProductCategoryId()).isEqualTo(requestDto.getProductCategoryId());
        assertThat(productResponse.getProductCategory().getName()).isEqualTo("Clothing");
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
        MockHttpServletRequestBuilder requestBuilder = post("/v1/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNotFound());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        ErrorResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        ProductErrorCode errorCode = ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND;

        assertThat(responseDto.isSuccess()).isFalse();
        assertThat(responseDto.getStatus()).isEqualTo(errorCode.getStatus());
        assertThat(responseDto.getCode()).isEqualTo(errorCode.getCode());
        assertThat(responseDto.getReason()).isEqualTo(errorCode.getReason());
    }

}