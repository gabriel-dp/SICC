package src.controller;

import src.model.User;

public class AppController {

    private static AppController instance = null;
    private static User userAuthenticated = null;

    public static AppController getInstance() {
        if (instance == null)
            instance = new AppController();
        return instance;
    }

    public User getUserAuthenticated() {
        return userAuthenticated;
    }

    public boolean login(String username, String password) {
        userAuthenticated = UserAuthenticator.getInstance().authenticate(username, password);
        return userAuthenticated != null;
    }

    public void logout() {
        userAuthenticated = null;
        UserAuthenticator.getInstance().refresh();
    }

}
