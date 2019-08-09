package ua.gladiator.model.entity.exception;

public class TakeNotFoundException extends RuntimeException {
    public TakeNotFoundException(String message) {
        super(message);
    }
}
