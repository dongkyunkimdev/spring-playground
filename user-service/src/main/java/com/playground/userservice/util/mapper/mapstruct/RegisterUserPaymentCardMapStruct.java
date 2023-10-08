package com.playground.userservice.util.mapper.mapstruct;

import com.playground.userservice.application.port.in.usecase.dto.RegisterUserPaymentCardInfo;
import com.playground.userservice.domain.UserPaymentCard;
import com.playground.userservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface RegisterUserPaymentCardMapStruct {

    RegisterUserPaymentCardInfo entityToInfo(UserPaymentCard userPaymentCard);

}
