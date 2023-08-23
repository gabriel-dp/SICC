import model.*;
import persistence.DataManager;
import view.AppView;

public class Program {

    public static void main(String[] args) {

        DataManager<User> userManager = new DataManager<>(User.class);
        userManager.create(new UserAdmin("admin", "12345", "TESTE", "TESTADOR"));

        AppView.getInstance().setVisible(true);

    }

}