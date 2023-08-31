package src;

import src.controller.DataController;
import src.model.*;
import src.view.AppView;

public class Program {

    public static void main(String[] args) {

        try {
            DataController<User> userManager = new DataController<>(User.class);
            userManager.create(new UserAdmin("admin", "12345", "Teste", "Admin"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        AppView.getInstance().setVisible(true);

    }

}