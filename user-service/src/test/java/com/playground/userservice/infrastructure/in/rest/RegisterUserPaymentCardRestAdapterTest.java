package com.playground.userservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.userservice.domain.enums.CardProvider;
import com.playground.userservice.domain.enums.CardType;
import com.playground.userservice.domain.exception.UserErrorCode;
import com.playground.userservice.infrastructure.in.rest.dto.RegisterUserPaymentCardRequest;
import com.playground.userservice.infrastructure.in.rest.dto.RegisterUserPaymentCardResponse;
import com.playground.userservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.playground.userservice.support.AssertUtil.assertErrorResponse;
import static com.playground.userservice.support.AssertUtil.assertSuccessResponse;
import static com.playground.userservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class RegisterUserPaymentCardRestAdapterTest extends ControllerTest {

    @Test
    void 결제카드_등록_성공() throws Exception {
        // given
        final Long userId = 6L;
        final RegisterUserPaymentCardRequest requestDto = new RegisterUserPaymentCardRequest(CardType.CREDIT_CARD, CardProvider.SAMSUNG, "1234567890");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/{userId}/payment-card", userId)
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isCreated());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.CREATED);

        RegisterUserPaymentCardResponse paymentCardResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(paymentCardResponse.userPaymentCardId()).isNotNull();
    }

    @Test
    void 결제카드_등록_실패_카드가_이미_등록됨() throws Exception {
        // given
        final Long userId = 5L;
        final RegisterUserPaymentCardRequest requestDto = new RegisterUserPaymentCardRequest(CardType.CREDIT_CARD, CardProvider.SAMSUNG, "1234567890");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/{userId}/payment-card", userId)
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isConflict());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.USER_PAYMENT_CARD_ALREADY_REGISTERED);
    }

    @Test
    void 결제카드_등록_실패_유저가_존재하지_않음() throws Exception {
        // given
        final Long userId = 1872361L;
        final RegisterUserPaymentCardRequest requestDto = new RegisterUserPaymentCardRequest(CardType.CREDIT_CARD, CardProvider.SAMSUNG, "1234567890");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/{userId}/payment-card", userId)
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNotFound());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.USER_NOT_FOUND);
    }

}
