package cz.upce.carsharing.exceptions;

public class AuthCheckException extends RuntimeException {
    public AuthCheckException(String message) {
        super(message);
    }
}
