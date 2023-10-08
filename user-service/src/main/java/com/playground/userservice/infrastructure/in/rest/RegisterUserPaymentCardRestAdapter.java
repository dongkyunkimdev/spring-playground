package com.playground.userservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.userservice.application.port.in.usecase.RegisterUserPaymentCardUseCase;
import com.playground.userservice.infrastructure.in.rest.docs.RegisterUserPaymentCardExceptionDocs;
import com.playground.userservice.infrastructure.in.rest.dto.RegisterUserPaymentCardRequest;
import com.playground.userservice.infrastructure.in.rest.dto.RegisterUserPaymentCardResponse;
import com.playground.userservice.util.mapper.RegisterUserPaymentCardMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class RegisterUserPaymentCardRestAdapter {

    private final RegisterUserPaymentCardUseCase userPaymentCardUseCase;

    private final RegisterUserPaymentCardMapper mapper;

    @Operation(summary = "결제 카드 등록.")
    @Tag(name = "2-1. [결제 카드 등록]")
    @ApiExceptionExample(RegisterUserPaymentCardExceptionDocs.class)
    @PostMapping(value = "/v1/users/{userId}/payment-card", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterUserPaymentCardResponse registerUserPaymentCard(
        @Parameter(description = "유저 ID.", example = "1") @PathVariable("userId") final Long userId,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "결제카드 정보.") @RequestBody(required = true) @Valid final RegisterUserPaymentCardRequest request
    ) {
        return mapper.infoToResponse(userPaymentCardUseCase.execute(mapper.requestToCommand(userId, request)));
    }

}
