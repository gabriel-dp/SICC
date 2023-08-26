package src.controller.auth;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String username) {
        super(String.format("User not found '%s'", username));
    }

}
