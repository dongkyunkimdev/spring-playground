package com.playground.userservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.userservice.application.port.in.usecase.RegisterUserPaymentCardUseCase;
import com.playground.userservice.application.port.in.usecase.dto.RegisterUserPaymentCardCommand;
import com.playground.userservice.application.port.in.usecase.dto.RegisterUserPaymentCardInfo;
import com.playground.userservice.application.port.out.persistence.UserPersistencePort;
import com.playground.userservice.application.port.out.external.PaymentCardExternalPort;
import com.playground.userservice.domain.User;
import com.playground.userservice.domain.exception.AlreadyRegisteredUserPaymentCardException;
import com.playground.userservice.domain.exception.UserNotFoundException;
import com.playground.userservice.util.mapper.RegisterUserPaymentCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RegisterUserPaymentCardService implements RegisterUserPaymentCardUseCase {

    private final UserPersistencePort userPersistencePort;

    private final PaymentCardExternalPort paymentCardExternalPort;

    private final RegisterUserPaymentCardMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public RegisterUserPaymentCardInfo execute(RegisterUserPaymentCardCommand command) {
        validateAlreadyRegisteredPaymentCard(command.userId(), command.number());
        User savedUser = getUser(command.userId());
        validateCardIdentityAndEligibility(command, savedUser);

        return mapper.entityToInfo(userPersistencePort.saveUserPaymentCard(mapper.commandToEntity(command, savedUser)));
    }

    private void validateAlreadyRegisteredPaymentCard(Long userId, String number) {
        if (userPersistencePort.isExistsUserPaymentCardByUserIdAndCardNumber(userId, number)) {
            throw new AlreadyRegisteredUserPaymentCardException();
        }
    }

    private User getUser(Long userId) {
        return userPersistencePort.searchUserById(userId)
            .orElseThrow(UserNotFoundException::new);
    }

    private void validateCardIdentityAndEligibility(RegisterUserPaymentCardCommand command, User savedUser) {
        paymentCardExternalPort.validateCardOwnerIdentity(savedUser.getFirstName(), savedUser.getLastName(), command.type(), command.provider(), command.number());
        paymentCardExternalPort.validateCardRegistrationEligibility(savedUser.getFirstName(), savedUser.getLastName(), command.type(), command.provider(), command.number());
    }

}
