package src.controller.auth;

import src.controller.DataController;
import src.model.User;

public final class UserAuthenticator {

    private UserAuthenticator() {
        // Prevents instantiation
    }

    public static User authenticate(String username, String password, DataController<User> controllerUser)
            throws UserNotFoundException, UserCredentialsException {

        User foundUser = controllerUser.search(username);
        if (foundUser == null) {
            throw new UserNotFoundException(username);
        } else if (!foundUser.getPassword().equals(password)) {
            throw new UserCredentialsException(username, password);
        }

        return foundUser;
    }

}
