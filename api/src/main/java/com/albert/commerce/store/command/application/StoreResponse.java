package com.albert.commerce.store.command.application;

import com.albert.commerce.store.command.domain.Store;
import com.albert.commerce.store.command.domain.StoreId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class StoreResponse extends RepresentationModel<StoreResponse> {

    private StoreId storeId;
    private String storeName;

    public StoreResponse() {
    }

    @Builder
    public StoreResponse(StoreId storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public static StoreResponse from(Store store) {
        return StoreResponse.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .build();
    }


}
