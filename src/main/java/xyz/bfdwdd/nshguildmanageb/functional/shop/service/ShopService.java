package xyz.bfdwdd.nshguildmanageb.functional.shop.service;

import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.request.CreateProductRequest;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.request.PurchaseProductRequest;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.response.ProductResponse;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.response.PurchaseRecordResponse;

import java.util.List;

public interface ShopService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    PurchaseRecordResponse purchaseProduct(PurchaseProductRequest request);
}