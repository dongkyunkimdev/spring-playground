package com.playground.userservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterUserPaymentCardResponse(
    @Schema(description = "유저 결제 카드 ID", example = "1") Long userPaymentCardId
) {

}
