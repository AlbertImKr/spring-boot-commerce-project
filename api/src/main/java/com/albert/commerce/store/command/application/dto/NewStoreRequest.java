package com.albert.commerce.store.command.application.dto;

import com.albert.commerce.store.command.domain.Store;
import com.albert.commerce.user.command.domain.UserId;
import lombok.Builder;

@Builder
public record NewStoreRequest(String storeName, String ownerName, String address,
                              String phoneNumber,

                              String email) {

    public Store toStore(UserId userId) {
        return Store.builder()
                .userId(userId)
                .storeName(storeName)
                .ownerName(ownerName)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}
