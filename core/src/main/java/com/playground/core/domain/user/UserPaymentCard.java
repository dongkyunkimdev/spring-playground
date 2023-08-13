package com.playground.core.domain.user;

import com.playground.core.type.CardProvider;
import com.playground.core.type.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_payment_card")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
