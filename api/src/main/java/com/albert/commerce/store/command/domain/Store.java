package com.albert.commerce.store.command.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "store")
public class Store {


    @EmbeddedId
    private StoreId storeId;

    private String storeName;

    private StoreUserId storeUserId;

    public Store(String storeName, StoreUserId storeUserId) {
        this.storeId = new StoreId();
        this.storeName = storeName;
        this.storeUserId = storeUserId;
    }
}
