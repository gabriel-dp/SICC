package controller;

import model.User;
import view.AppFrame;

public class AppController {

    private UserAuthenticator auth = new UserAuthenticator();
    private User userAuthenticated = null;

    public void start() {
        new AppFrame(this).setVisible(true);
    }

    public boolean login(String username, String password) {
        userAuthenticated = auth.authenticate(username, password);
        return userAuthenticated != null;
    }

    public void logout() {
        userAuthenticated = null;
    }

    public User getUserAuthenticated() {
        return userAuthenticated;
    }

}
