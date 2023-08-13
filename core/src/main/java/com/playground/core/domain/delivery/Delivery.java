package com.playground.core.domain.delivery;

import com.playground.core.type.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(name = "arrival_at")
    private Date arrivalAt;

    @Column(name = "order_id")
    private Long orderId;

}
