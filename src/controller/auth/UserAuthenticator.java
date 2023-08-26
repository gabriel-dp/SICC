package src.controller.auth;

import java.util.HashMap;

import src.model.User;
import src.persistence.DataManager;

public class UserAuthenticator {

    private HashMap<String, User> usernameHash = new HashMap<>();

    private static UserAuthenticator instance = null;

    private UserAuthenticator() {
        fillUsernameHash();
    }

    public static UserAuthenticator getInstance() {
        if (instance == null)
            instance = new UserAuthenticator();
        return instance;
    }

    private void fillUsernameHash() {
        for (User user : new DataManager<>(User.class).getAllData()) {
            usernameHash.put(user.getUsername(), user);
        }
    }

    public void refresh() {
        usernameHash.clear();
        fillUsernameHash();
    }

    public User authenticate(String username, String password) throws UserNotFoundException, UserCredentialsException {
        User foundUser = usernameHash.get(username);

        if (foundUser == null) {
            throw new UserNotFoundException(username);
        } else if (!foundUser.getPassword().equals(password)) {
            throw new UserCredentialsException(username, password);
        }

        return foundUser;
    }

}
