package xyz.bfdwdd.nshguildmanageb.functional.shop.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}