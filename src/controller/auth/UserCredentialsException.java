package src.controller.auth;

public class UserCredentialsException extends Exception {

    public UserCredentialsException(String username, String password) {
        super(String.format("Incorrect credentials ('%s', '%s')", username, password));
    }

}
