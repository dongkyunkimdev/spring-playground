package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class RegisterProductCategoryRestAdapterTest extends ControllerTest {

    @Test
    void 상품_카테고리_등록_성공() throws Exception {
        // given
        final String name = "Toys";
        RegisterProductCategoryRequest requestDto = RegisterProductCategoryRequest.builder()
                .name(name)
                .build();

        // when
        MockHttpServletRequestBuilder request = post("/v1/product/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(request);

        // then
        result.andExpect(status().isCreated());

        String responseMessage = result.andReturn().getResponse().getContentAsString();
        RegisterProductCategoryResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.getName()).isEqualTo(name);
    }

}
