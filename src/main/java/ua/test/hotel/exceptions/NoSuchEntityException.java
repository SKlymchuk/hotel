package ua.test.hotel.exceptions;

public class NoSuchEntityException extends Exception {

    long id;

    public long getId() {
        return id;
    }

    public NoSuchEntityException(String message, long id) {
        super(message + id);
        this.id = id;
    }
}
