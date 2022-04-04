package cz.upce.carsharing.exceptions;

public class RentCarException extends RuntimeException {
    public RentCarException(String message) {
        super(message);
    }
}
