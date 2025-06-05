package xyz.bfdwdd.nshguildmanageb.functional.shop.exception;

public class PurchaseFailedException extends RuntimeException {
    public PurchaseFailedException(String message) {
        super(message);
    }
}