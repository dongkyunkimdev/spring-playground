package com.playground.userservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.userservice.application.port.in.usecase.GetUserListUseCase;
import com.playground.userservice.application.port.in.usecase.dto.GetUserListCommand;
import com.playground.userservice.application.port.in.usecase.dto.GetUserListInfo;
import com.playground.userservice.application.port.out.persistence.UserPersistencePort;
import com.playground.userservice.domain.User;
import com.playground.userservice.util.mapper.GetUserListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetUserListService implements GetUserListUseCase {

    private final UserPersistencePort userPersistencePort;

    private final GetUserListMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Slice<GetUserListInfo> execute(GetUserListCommand command, Pageable pageable) {
        Slice<User> userSlice = userPersistencePort.searchUserListBySearchCondition(mapper.commandToSearchCondition(command), pageable);

        return userSlice.map(mapper::entityToInfo);
    }

}
