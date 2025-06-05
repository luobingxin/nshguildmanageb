package xyz.bfdwdd.nshguildmanageb.functional.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.request.CreateProductRequest;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.request.PurchaseProductRequest;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.response.ProductResponse;
import xyz.bfdwdd.nshguildmanageb.functional.shop.dto.response.PurchaseRecordResponse;
import xyz.bfdwdd.nshguildmanageb.functional.shop.entity.Product;
import xyz.bfdwdd.nshguildmanageb.functional.shop.entity.PurchaseRecord;
import xyz.bfdwdd.nshguildmanageb.functional.shop.exception.InsufficientBalanceException;
import xyz.bfdwdd.nshguildmanageb.functional.shop.exception.ProductNotFoundException;
import xyz.bfdwdd.nshguildmanageb.functional.shop.repository.ProductRepository;
import xyz.bfdwdd.nshguildmanageb.functional.shop.repository.PurchaseRecordRepository;
import xyz.bfdwdd.nshguildmanageb.functional.shop.service.ShopService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ProductRepository productRepository;
    private final PurchaseRecordRepository purchaseRecordRepository;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return mapToResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
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
    public PurchaseRecordResponse purchaseProduct(PurchaseProductRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("商品未找到"));

        if (product.getStock() < request.getQuantity()) {
            throw new InsufficientBalanceException("库存不足");
        }

        PurchaseRecord record = new PurchaseRecord();
        record.setUserId(request.getUserId());
        record.setProductId(request.getProductId());
        record.setQuantity(request.getQuantity());
        record.setPurchaseTime(LocalDateTime.now());

        product.setStock(product.getStock() - request.getQuantity());
        productRepository.save(product);

        return mapToResponse(purchaseRecordRepository.save(record));
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        return response;
    }

    private PurchaseRecordResponse mapToResponse(PurchaseRecord record) {
        PurchaseRecordResponse response = new PurchaseRecordResponse();
        response.setId(record.getId());
        response.setUserId(record.getUserId());
        response.setProductId(record.getProductId());
        response.setQuantity(record.getQuantity());
        response.setPurchaseTime(record.getPurchaseTime());
        return response;
    }
}