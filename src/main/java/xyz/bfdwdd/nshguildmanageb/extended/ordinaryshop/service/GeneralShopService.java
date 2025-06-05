package xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.service;

import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.request.CreateProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.request.PurchaseProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.response.ProductResponse;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.response.ReviewResponse;

import java.util.List;

public interface GeneralShopService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    ReviewResponse approveProduct(Long productId, String adminId);
    ProductResponse purchaseProduct(PurchaseProductRequest request);
}