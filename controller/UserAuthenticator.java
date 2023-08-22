package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.User;
import persistence.DataManager;

public class UserAuthenticator {

    private HashMap<String, User> usernameHash;
    private DataManager<User> dataManager = new DataManager<>(User.class);

    public UserAuthenticator() {
        usernameHash = new HashMap<>();
        fillHash();
    }

    private void fillHash() {
        ArrayList<User> allUsers = dataManager.getAllData();
        for (User user : allUsers) {
            usernameHash.put(user.getUsername(), user);
        }
    }

    public User authenticate(String username, String password) {
        User findedUser = usernameHash.get(username);
        if (findedUser == null || !findedUser.getPassword().equals(password))
            return null;
        return findedUser;
    }

    public void refresh() {
        usernameHash.clear();
        fillHash();
    }

}
