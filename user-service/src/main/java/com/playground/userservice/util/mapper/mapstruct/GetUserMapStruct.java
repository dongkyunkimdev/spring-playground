package com.playground.userservice.util.mapper.mapstruct;

import com.playground.userservice.application.port.in.usecase.dto.GetUserInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserResponse;
import com.playground.userservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetUserMapStruct {

    GetUserInfo entityToInfo(User user);

    GetUserResponse infoToResponse(GetUserInfo info);

}
