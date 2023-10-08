package com.playground.userservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.userservice.application.port.in.usecase.dto.RegisterUserPaymentCardCommand;
import com.playground.userservice.application.port.in.usecase.dto.RegisterUserPaymentCardInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.domain.UserPaymentCard;
import com.playground.userservice.util.mapper.mapstruct.RegisterUserPaymentCardMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class RegisterUserPaymentCardMapper {

    private final RegisterUserPaymentCardMapStruct mapStruct;

    public UserPaymentCard commandToEntity(RegisterUserPaymentCardCommand command, User user) {
        return UserPaymentCard.builder()
            .type(command.type())
            .provider(command.provider())
            .number(command.number())
            .user(user)
            .build();
    }

    public RegisterUserPaymentCardInfo entityToInfo(UserPaymentCard userPaymentCard) {
        return mapStruct.entityToInfo(userPaymentCard);
    }

}
