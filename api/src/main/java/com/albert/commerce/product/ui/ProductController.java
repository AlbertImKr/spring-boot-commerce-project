package com.albert.commerce.product.ui;

import com.albert.commerce.common.units.BusinessLinks;
import com.albert.commerce.product.command.application.ProductRequest;
import com.albert.commerce.product.command.application.dto.ProductCreatedResponse;
import com.albert.commerce.product.command.application.dto.ProductResponse;
import com.albert.commerce.product.command.application.dto.ProductService;
import com.albert.commerce.product.command.domain.ProductId;
import com.albert.commerce.product.query.ProductFacade;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/products", produces = MediaTypes.HAL_JSON_VALUE)
public class ProductController {

    private final ProductService productService;
    private final ProductFacade productFacade;

    @PostMapping
    public ResponseEntity<ProductCreatedResponse> addProduct(
            @RequestBody ProductRequest productRequest,
            Principal principal) {
        String userEmail = principal.getName();
        ProductCreatedResponse productResponse = productService.addProduct(productRequest,
                userEmail);
        return ResponseEntity.created(BusinessLinks.MY_STORE.toUri())
                .body(productResponse);
    }

    @GetMapping
    public ResponseEntity<PagedModel<ProductResponse>> getAllProducts(Principal principal,
            Pageable pageable) {
        return ResponseEntity.ok(
                productFacade.findProductsByUserEmail(principal.getName(), pageable));
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(Principal principal,
            @PathVariable ProductId productId, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.update(principal.getName(), productId,
                productRequest));
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable ProductId productId) {
        return ResponseEntity.ok(productFacade.findById(productId));
    }
}
