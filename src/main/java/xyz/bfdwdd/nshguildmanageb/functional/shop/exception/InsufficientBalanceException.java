package xyz.bfdwdd.nshguildmanageb.functional.shop.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}