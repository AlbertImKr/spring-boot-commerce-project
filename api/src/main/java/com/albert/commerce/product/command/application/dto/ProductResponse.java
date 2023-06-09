package com.albert.commerce.product.command.application.dto;

import com.albert.commerce.common.infra.persistence.Money;
import com.albert.commerce.common.units.BusinessLinks;
import com.albert.commerce.product.command.domain.Product;
import com.albert.commerce.product.command.domain.ProductId;
import com.albert.commerce.store.command.domain.StoreId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;


@Getter
public class ProductResponse extends RepresentationModel<ProductResponse> {

    private final ProductId productId;
    private final String productName;
    private final Money price;
    private final String description;
    private final String brand;
    private final String category;
    private final StoreId storeId;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdTime;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
    private LocalDateTime updateTime;

    @Builder
    private ProductResponse(ProductId productId, String productName, Money price,
            String description,
            String brand, String category, LocalDateTime createdTime, LocalDateTime updateTime,
            Links links, StoreId storeId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
        this.storeId = storeId;
        add(links);
    }

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .createdTime(product.getCreatedTime())
                .updateTime(product.getUpdateTime())
                .productId(product.getProductId())
                .productName(product.getProductName())
                .brand(product.getBrand())
                .storeId(product.getStoreId())
                .description(product.getDescription())
                .category(product.getCategory())
                .links(Links.of(BusinessLinks.getProductSelfRel(product.getProductId())))
                .price(product.getPrice())
                .build();
    }
}
