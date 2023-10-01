package com.playground.userservice.util.mapper.mapstruct;

import com.playground.userservice.application.port.in.usecase.dto.GetUserListCommand;
import com.playground.userservice.application.port.in.usecase.dto.GetUserListInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.dao.dto.GetUserListSearchCondition;
import com.playground.userservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetUserListMapStruct {

    GetUserListSearchCondition commandToSearchCondition(GetUserListCommand command);

    GetUserListInfo entityToInfo(User user);

}
