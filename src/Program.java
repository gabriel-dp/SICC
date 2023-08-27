package src;

import src.controller.DataController;
import src.model.Professor;
import src.model.User;
import src.model.UserAdmin;
import src.model.UserStudent;
import src.view.AppView;

public class Program {

    public static void main(String[] args) {

        DataController<User> userManager = new DataController<>(User.class);
        userManager.create(new UserAdmin("admin", "12345", "Teste", "Admin"));
        userManager.create(new UserStudent("student", "12345", "Testa", "Estuda", null));
        DataController<Professor> pm = new DataController<>(Professor.class);
        pm.create(new Professor("Matheus", "Viana", "null@gmail.com"));

        AppView.getInstance().setVisible(true);

    }

}