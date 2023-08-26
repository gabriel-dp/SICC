import model.*;
import persistence.DataManager;
import view.AppView;

public class Program {

    public static void main(String[] args) {

        DataManager<User> userManager = new DataManager<>(User.class);
        userManager.create(new UserAdmin("admin", "12345", "Teste", "Admin"));
        userManager.create(new UserStudent("student", "12345", "Testa", "Estuda", null));

        AppView.getInstance().setVisible(true);

    }

}