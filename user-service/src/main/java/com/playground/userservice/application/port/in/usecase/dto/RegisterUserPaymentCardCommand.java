package com.playground.userservice.application.port.in.usecase.dto;

import com.playground.userservice.domain.enums.CardProvider;
import com.playground.userservice.domain.enums.CardType;

public record RegisterUserPaymentCardCommand(
    Long userId,
    CardType type,
    CardProvider provider,
    String number
) {

}
