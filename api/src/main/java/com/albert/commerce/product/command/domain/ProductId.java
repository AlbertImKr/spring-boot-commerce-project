package com.albert.commerce.product.command.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Access(AccessType.FIELD)
public class ProductId implements Serializable {

    @Column(name = "product_id")
    private UUID id;

    public ProductId() {
        this.id = UUID.randomUUID();
    }

    @JsonValue
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductId productId)) {
            return false;
        }
        return Objects.equals(getId(), productId.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}