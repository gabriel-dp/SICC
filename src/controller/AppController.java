package src.controller;

import src.controller.auth.*;
import src.model.*;
import src.view.AppView;

public class AppController {

    private static User userAuthenticated = null;

    // Individual data controllers
    private static DataController<User> controllerUser = new DataController<>(User.class);
    private static DataController<Course> controllerCourse = new DataController<>(Course.class);
    private static DataController<Subject> controllerSubject = new DataController<>(Subject.class);
    private static DataController<Professor> controllerProfessor = new DataController<>(Professor.class);

    private AppController() {
        // Prevents instantiation
    }

    public static void login(String username, String password) throws UserNotFoundException, UserCredentialsException {
        userAuthenticated = UserAuthenticator.authenticate(username, password, controllerUser);
        AppView.getInstance().showServices();
    }

    public static void logout() {
        userAuthenticated = null;
        AppView.getInstance().showAuth();
    }

    public static User getUserAuthenticated() {
        return userAuthenticated;
    }

    public static DataController<User> getControllerUser() {
        return controllerUser;
    }

    public static DataController<Course> getControllerCourse() {
        return controllerCourse;
    }

    public static DataController<Subject> getControllerSubject() {
        return controllerSubject;
    }

    public static DataController<Professor> getControllerProfessor() {
        return controllerProfessor;
    }
}
