package src.controller;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException(String message) {
        super(String.format(message));
    }
}