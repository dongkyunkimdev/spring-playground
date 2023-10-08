package com.playground.userservice.infrastructure.in.rest.dto;

import com.playground.userservice.domain.enums.CardProvider;
import com.playground.userservice.domain.enums.CardType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserPaymentCardRequest(
    @NotNull(message = "type은 비어있을 수 없습니다.")
    @Schema(description = "카드 종류", example = "CREDIT_CARD") CardType type,

    @NotNull(message = "provider는 비어있을 수 없습니다.")
    @Schema(description = "카드사", example = "SAMSUNG") CardProvider provider,

    @NotBlank(message = "number은 비어있을 수 없습니다.")
    @Schema(description = "카드 번호", example = "1234567890") String number
) {

}
