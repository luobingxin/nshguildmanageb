package xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.request.CreateProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.request.PurchaseProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.response.ProductResponse;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.response.ReviewResponse;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.entity.GeneralProduct;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.entity.ProductReview;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.exception.ProductNotFoundException;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.exception.ReviewNotApprovedException;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.repository.GeneralProductRepository;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.repository.ProductReviewRepository;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.service.GeneralShopService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneralShopServiceImpl implements GeneralShopService {

    private final GeneralProductRepository productRepository;
    private final ProductReviewRepository reviewRepository;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        GeneralProduct product = new GeneralProduct();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCurrencyType(request.getCurrencyType());

        return mapToResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getProductById(Long id) {
        GeneralProduct product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("商品未找到"));
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse approveProduct(Long productId, String adminId) {
        GeneralProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("商品未找到"));

        if (!"SYSTEM_ADMIN".equals(adminId)) {
            throw new IllegalArgumentException("只有系统管理员可审核");
        }

        ProductReview review = new ProductReview();
        review.setProductId(productId);
        review.setReviewerId(adminId);
        review.setStatus("APPROVED");
        review.setReviewTime(LocalDateTime.now());

        return mapToReviewResponse(reviewRepository.save(review));
    }

    @Override
    public ProductResponse purchaseProduct(PurchaseProductRequest request) {
        GeneralProduct product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("商品未找到"));

        ProductReview review = reviewRepository.findByProductId(request.getProductId())
                .stream().filter(r -> r.getStatus().equals("APPROVED"))
                .findFirst()
                .orElseThrow(() -> new ReviewNotApprovedException("商品未通过审核"));

        if (product.getStock() < request.getQuantity()) {
            throw new IllegalArgumentException("库存不足");
        }

        product.setStock(product.getStock() - request.getQuantity());
        return mapToResponse(productRepository.save(product));
    }

    private ProductResponse mapToResponse(GeneralProduct product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCurrencyType(product.getCurrencyType());
        return response;
    }

    private ReviewResponse mapToReviewResponse(ProductReview review) {
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setProductId(review.getProductId());
        response.setReviewerId(review.getReviewerId());
        response.setStatus(review.getStatus());
        response.setReviewTime(review.getReviewTime());
        return response;
    }
}