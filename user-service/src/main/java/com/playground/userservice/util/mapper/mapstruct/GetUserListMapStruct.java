package com.playground.userservice.util.mapper.mapstruct;

import com.playground.userservice.application.port.in.usecase.dto.GetUserListCommand;
import com.playground.userservice.application.port.in.usecase.dto.GetUserListInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.dao.dto.GetUserListSearchCondition;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserListRequest;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserListResponse;
import com.playground.userservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetUserListMapStruct {

    GetUserListCommand requestToCommand(GetUserListRequest request);

    GetUserListInfo entityToInfo(User user);

    GetUserListResponse infoToResponse(GetUserListInfo info);

    GetUserListSearchCondition commandToSearchCondition(GetUserListCommand command);

}
