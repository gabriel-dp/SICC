package src.controller;

import java.util.ArrayList;
import java.util.HashMap;

import src.model.User;
import src.persistence.DataManager;

public class UserAuthenticator {

    private static UserAuthenticator instance = null;

    private HashMap<String, User> usernameHash = new HashMap<>();

    private UserAuthenticator() {
        refresh();
    }

    public static UserAuthenticator getInstance() {
        if (instance == null)
            instance = new UserAuthenticator();
        return instance;
    }

    private void fillHash() {
        ArrayList<User> allUsers = new DataManager<>(User.class).getAllData();
        for (User user : allUsers) {
            usernameHash.put(user.getUsername(), user);
        }
    }

    public void refresh() {
        usernameHash.clear();
        fillHash();
    }

    public User authenticate(String username, String password) {
        User findedUser = usernameHash.get(username);
        if (findedUser == null || !findedUser.getPassword().equals(password))
            return null;
        return findedUser;
    }

}
