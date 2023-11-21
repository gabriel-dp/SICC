package src.controller;

import src.controller.auth.*;
import src.model.*;
import src.view.View;

public class AppController {

    private static User userAuthenticated = null;

    // Individual data controllers
    private static DataController<User> controllerUser = new DataController<>(User.class);
    private static DataController<Course> controllerCourse = new DataController<>(Course.class);
    private static DataController<Subject> controllerSubject = new DataController<>(Subject.class);
    private static DataController<Professor> controllerProfessor = new DataController<>(Professor.class);

    private static View view = null;

    private AppController(View view) {
        // Prevents instanciating
    }

    public static void login(String username, String password) throws UserNotFoundException, UserCredentialsException {
        userAuthenticated = UserAuthenticator.authenticate(username, password, controllerUser);
        if (view != null)
            view.showServices();
    }

    public static void logout() {
        userAuthenticated = null;
        if (view != null)
            view.showAuth();
    }

    public static void displayGUI(View newView) {
        view = newView;

        view.display();
        if (userAuthenticated == null)
            view.showAuth();
        else
            view.showServices();
    }

    public static void createDefaultUser() {
        try {
            controllerUser.create(new UserAdmin("admin", "12345", "Teste", "Admin"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
