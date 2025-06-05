package xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}