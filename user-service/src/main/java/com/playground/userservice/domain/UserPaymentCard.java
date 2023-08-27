package com.playground.userservice.domain;

import com.playground.core.entity.BaseTimeEntity;
import com.playground.core.enums.CardProvider;
import com.playground.core.enums.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_payment_card")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPaymentCard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_payment_card_id")
    private Long userPaymentCardId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CardType type;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private CardProvider provider;

    @Column(name = "number")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

}
