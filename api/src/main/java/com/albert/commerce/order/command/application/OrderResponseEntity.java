package com.albert.commerce.order.command.application;

import com.albert.commerce.order.command.domain.DeliveryStatus;
import com.albert.commerce.order.command.domain.OrderId;
import com.albert.commerce.order.query.application.OrderDetail;
import com.albert.commerce.order.ui.OrderController;
import com.albert.commerce.product.command.application.dto.ProductResponse;
import com.albert.commerce.store.command.domain.StoreId;
import com.albert.commerce.user.command.domain.UserId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Getter
public class OrderResponseEntity extends RepresentationModel<ProductResponse> {

    private final OrderId orderId;
    private final UserId userId;
    private final StoreId storeId;
    private final List<ProductResponse> products;
    private final DeliveryStatus deliveryStatus;
    private final long amount;
    private final LocalDateTime createdTime;

    @Builder
    protected OrderResponseEntity(Links links, OrderId orderId,
            UserId userId, StoreId storeId, List<ProductResponse> products,
            DeliveryStatus deliveryStatus, long amount, LocalDateTime createdTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.storeId = storeId;
        this.products = products;
        this.deliveryStatus = deliveryStatus;
        this.amount = amount;
        this.createdTime = createdTime;
        this.add(links);
    }

    public static OrderResponseEntity from(OrderDetail order) {
        return OrderResponseEntity.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .storeId(order.getStoreId())
                .products(order.getProducts().stream()
                        .map(ProductResponse::from)
                        .collect(Collectors.toList()))
                .deliveryStatus(order.getDeliveryStatus())
                .amount(order.getAmount())
                .links(Links.of(WebMvcLinkBuilder.linkTo(OrderController.class)
                        .slash(order.getOrderId().getId())
                        .withSelfRel()))
                .createdTime(order.getCreatedTime())
                .build();
    }
}
