package Exceptions;
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
