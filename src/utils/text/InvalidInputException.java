package src.utils.text;

public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super("Invalid input");
    }

    public InvalidInputException(String message) {
        super("Invalid input - " + message);
    }

}