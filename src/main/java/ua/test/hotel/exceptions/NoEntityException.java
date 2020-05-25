package ua.test.hotel.exceptions;

public class NoEntityException extends Exception {

    long id;

    public long getId() {
        return id;
    }

    public NoEntityException(String message, long id) {
        super(message + id);
        this.id = id;
    }
}
