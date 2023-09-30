package com.playground.userservice.util.mapper.mapstruct;

import com.playground.userservice.application.port.in.usecase.dto.SignupInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface SignupMapStruct {

    SignupInfo entityToInfo(User user);

}
