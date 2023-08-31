package src.controller.auth;

import src.controller.DataController;
import src.model.User;

public class UserAuthenticator {

    private static UserAuthenticator instance = null;

    public static UserAuthenticator getInstance() {
        if (instance == null)
            instance = new UserAuthenticator();
        return instance;
    }

    public User authenticate(String username, String password) throws UserNotFoundException, UserCredentialsException {
        User foundUser = new DataController<User>(User.class).search(username);

        if (foundUser == null) {
            throw new UserNotFoundException(username);
        } else if (!foundUser.getPassword().equals(password)) {
            throw new UserCredentialsException(username, password);
        }

        return foundUser;
    }

}
