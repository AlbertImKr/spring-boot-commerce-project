package com.albert.commerce.store.command.application;

import com.albert.commerce.common.BusinessLinks;
import com.albert.commerce.store.command.domain.Store;
import com.albert.commerce.store.command.domain.StoreId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ConsumerStoreResponse extends RepresentationModel<ConsumerStoreResponse> {

    private StoreId storeId;
    private String storeName;

    public ConsumerStoreResponse() {
    }

    @Builder
    public ConsumerStoreResponse(StoreId storeId, String storeName, Links links) {
        this.storeId = storeId;
        this.storeName = storeName;
        super.add(links);
    }

    public static ConsumerStoreResponse from(Store store) {
        return ConsumerStoreResponse.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .links(Links.of(BusinessLinks.GET_STORE))
                .build();
    }


}
