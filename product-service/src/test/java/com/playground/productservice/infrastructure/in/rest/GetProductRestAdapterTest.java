package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.productservice.infrastructure.in.rest.dto.CommonProductCategoryResponse;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class GetProductRestAdapterTest extends ControllerTest {

    @Test
    void 상품_조회_성공() throws Exception {
        // given
        final Long productId = 1L;

        // when
        MockHttpServletRequestBuilder requestBuilder = get("/v1/product/{productId}", productId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(200);

        GetProductResponse productResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productResponse.productId()).isEqualTo(productId);
        assertThat(productResponse.name()).isEqualTo("T-shirt");
        assertThat(productResponse.stock()).isEqualTo(100);
        assertThat(productResponse.price()).isEqualTo(BigDecimal.valueOf(19.99));

        CommonProductCategoryResponse productCategoryResponse = productResponse.productCategory();
        assertThat(productCategoryResponse.productCategoryId()).isEqualTo(1);
        assertThat(productCategoryResponse.name()).isEqualTo("Clothing");
    }

}