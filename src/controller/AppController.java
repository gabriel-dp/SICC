package src.controller;

import src.controller.auth.*;
import src.model.*;

public class AppController {

    private static User userAuthenticated = null;

    // Individual data controllers
    private static DataController<User> controllerUser = new DataController<>(User.class);
    private static DataController<Course> controllerCourse = new DataController<>(Course.class);
    private static DataController<Subject> controllerSubject = new DataController<>(Subject.class);
    private static DataController<Professor> controllerProfessor = new DataController<>(Professor.class);
    private static DataController<Curriculum> controllerCurriculum = new DataController<>(Curriculum.class);

    private AppController() {
        // Prevents instantiation
    }

    public static void login(String username, String password) throws UserNotFoundException, UserCredentialsException {
        userAuthenticated = UserAuthenticator.authenticate(username, password, controllerUser);
    }

    public static void logout() {
        userAuthenticated = null;
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

    public static DataController<Curriculum> getControllerCurriculum() {
        return controllerCurriculum;
    }

}
