package control;

import java.util.ArrayList;
import java.util.HashMap;

import model.User;
import persistence.DataManager;

public class UserAuthenticator {

    private HashMap<String, User> userCredentials;
    private DataManager<User> dataManager = new DataManager<>(User.class);

    public UserAuthenticator() {
        userCredentials = new HashMap<>();
        ArrayList<User> allUsers = dataManager.getAllData();
        for (User user : allUsers) {
            userCredentials.put(user.getUsername(), user);
        }
    }

    public User authenticate(String username, String password) {
        User findedUser = userCredentials.get(username);
        if (findedUser == null || !findedUser.getPassword().equals(password))
            return null;
        return findedUser;
    }

}
