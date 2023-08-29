package src.controller;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException(String entityName) {
        super(String.format("%s already exists", entityName));
    }

}