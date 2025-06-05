package xyz.bfdwdd.nshguildmanageb.functional.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.request.CreateProductRequest;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.request.PurchaseProductRequest;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.response.ProductResponse;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.response.PurchaseRecordResponse;
import xyz.bfdwdd.nshguildmanageb.functional.shop.exception.*;
import xyz.bfdwdd.nshguildmanageb.functional.shop.service.ShopService;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @Operation(summary = "获取所有商品", description = "Get all products in the shop")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(shopService.getAllProducts());
    }

    @Operation(summary = "根据ID获取商品", description = "Get a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "找到商品"),
            @ApiResponse(responseCode = "404", description = "未找到商品", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@Parameter(description = "商品的ID") @PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(shopService.getProductById(id));
    }

    @Operation(summary = "创建新商品", description = "Create a new product")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        return ResponseEntity.status(201).body(shopService.createProduct(request));
    }

    @Operation(summary = "购买商品", description = "Purchase a product")
    @PostMapping("/purchase")
    public ResponseEntity<PurchaseRecordResponse> purchaseProduct(@RequestBody PurchaseProductRequest request) throws ProductNotFoundException, InsufficientBalanceException {
        return ResponseEntity.ok(shopService.purchaseProduct(request));
    }
}