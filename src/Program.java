package src;

// import src.controller.DataController;
// import src.model.*;
import src.view.AppView;

public class Program {

    public static void main(String[] args) {

        // DataController<User> userManager = new DataController<>(User.class);
        // userManager.create(new UserAdmin("admin", "12345", "Teste", "Admin"));
        // userManager.create(new UserStudent("student", "12345", "Testa", "Estuda",
        // null));

        AppView.getInstance().setVisible(true);

    }

}