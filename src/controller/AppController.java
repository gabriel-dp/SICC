package src.controller;

import src.controller.auth.UserAuthenticator;
import src.controller.auth.UserCredentialsException;
import src.controller.auth.UserNotFoundException;
import src.model.User;

public class AppController {

    private static AppController instance = null;
    private User userAuthenticated = null;

    public static AppController getInstance() {
        if (instance == null)
            instance = new AppController();
        return instance;
    }

    public User getUserAuthenticated() {
        return userAuthenticated;
    }

    public void login(String username, String password) throws UserNotFoundException, UserCredentialsException {
        userAuthenticated = UserAuthenticator.getInstance().authenticate(username, password);
    }

    public void logout() {
        userAuthenticated = null;
    }

}
