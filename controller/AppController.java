package controller;

import model.User;

public class AppController {

    private UserAuthenticator auth = new UserAuthenticator();
    private User userAuthenticated = null;

    public boolean login(String username, String password) {
        userAuthenticated = auth.authenticate(username, password);
        return userAuthenticated != null;
    }

    public void logout() {
        userAuthenticated = null;
        auth.refresh();
    }

    public User getUserAuthenticated() {
        return userAuthenticated;
    }

}
