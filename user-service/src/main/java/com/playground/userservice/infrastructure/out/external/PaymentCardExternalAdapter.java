package com.playground.userservice.infrastructure.out.external;

import com.playground.core.annotation.ExternalAdapter;
import com.playground.userservice.application.port.out.rest.PaymentCardExternalPort;
import com.playground.userservice.domain.enums.CardProvider;
import com.playground.userservice.domain.enums.CardType;

@ExternalAdapter
public class PaymentCardExternalAdapter implements PaymentCardExternalPort {

    @Override
    public void validateCardOwnerIdentity(String firstName, String lastName, CardType type, CardProvider provider, String number) {
        // TODO: CardProvider별 API 호출 및 Exception 처리 로직 추가
    }

    @Override
    public void validateCardRegistrationEligibility(String firstName, String lastName, CardType type, CardProvider provider, String number) {
        // TODO: CardProvider별 API 호출 및 Exception 처리 로직 추가
    }

}
