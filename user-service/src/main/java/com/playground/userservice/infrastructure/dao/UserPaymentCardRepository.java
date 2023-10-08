package com.playground.userservice.infrastructure.dao;

import com.playground.userservice.domain.UserPaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentCardRepository extends JpaRepository<UserPaymentCard, Long> {

    boolean existsByUserUserIdAndNumber(Long userId, String number);

}
