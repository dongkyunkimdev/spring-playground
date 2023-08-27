package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class GetProductCategoryRestAdapterTest extends ControllerTest {

    @Test
    void 상품_카테고리_조회_성공() throws Exception {
        // given
        final Long productCategoryId = 1L;

        // when
        MockHttpServletRequestBuilder request = get("/v1/product/category/{productCategoryId}", productCategoryId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mvc.perform(request);

        // then
        result.andExpect(status().isOk());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(200);

        ProductCategory productCategory = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategory.getProductCategoryId()).isEqualTo(productCategoryId);
        assertThat(productCategory.getName()).isEqualTo("Clothing");
    }

}
