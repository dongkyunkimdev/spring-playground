package com.playground.core.domain.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "user_payment_card_id")
    private Long userPaymentCardId;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private Order order;

}
