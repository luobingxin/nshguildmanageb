package xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.controller;

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
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.request.CreateProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.request.PurchaseProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.response.ProductResponse;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.response.ReviewResponse;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.exception.ProductNotFoundException;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.exception.ReviewNotApprovedException;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.exception.InvalidRoleOperationException;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.service.GeneralShopService;

import java.util.List;

@RestController
@RequestMapping("/api/general-shop")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class GeneralShopController {

    private final GeneralShopService generalShopService;

    @Operation(summary = "获取所有商品", description = "Get all products in the general shop")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(generalShopService.getAllProducts());
    }

    @Operation(summary = "根据ID获取商品", description = "Get a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "找到商品"),
            @ApiResponse(responseCode = "404", description = "未找到商品", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@Parameter(description = "商品的ID") @PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(generalShopService.getProductById(id));
    }

    @Operation(summary = "创建新商品", description = "Create a new product (requires system admin approval)")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        return ResponseEntity.status(201).body(generalShopService.createProduct(request));
    }

    @Operation(summary = "审核商品", description = "Approve or reject a product")
    @PutMapping("/approve/{productId}")
    public ResponseEntity<ReviewResponse> approveProduct(
            @Parameter(description = "商品ID") @PathVariable Long productId,
            @RequestParam String adminId) {
        return ResponseEntity.ok(generalShopService.approveProduct(productId, adminId));
    }

    @Operation(summary = "购买商品", description = "Purchase a product with available balance")
    @PostMapping("/purchase")
    public ResponseEntity<ProductResponse> purchaseProduct(@RequestBody PurchaseProductRequest request) throws ProductNotFoundException, ReviewNotApprovedException {
        return ResponseEntity.ok(generalShopService.purchaseProduct(request));
    }
}