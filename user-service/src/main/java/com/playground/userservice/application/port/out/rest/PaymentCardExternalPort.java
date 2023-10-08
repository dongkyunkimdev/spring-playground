package com.playground.userservice.application.port.out.rest;

import com.playground.userservice.domain.enums.CardProvider;
import com.playground.userservice.domain.enums.CardType;

public interface PaymentCardExternalPort {

    void validateCardOwnerIdentity(String firstName, String lastName, CardType type, CardProvider provider, String number);

    void validateCardRegistrationEligibility(String firstName, String lastName, CardType type, CardProvider provider, String number);

}
