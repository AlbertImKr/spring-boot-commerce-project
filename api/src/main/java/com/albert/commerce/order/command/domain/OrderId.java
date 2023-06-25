package com.albert.commerce.order.command.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Access(AccessType.FIELD)
public class OrderId implements Serializable {

    @Column(name = "order_id", nullable = false)
    private String value;

    private OrderId(String orderId) {
        this.value = orderId;
    }

    public static OrderId from(String orderId) {
        return new OrderId(orderId);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
