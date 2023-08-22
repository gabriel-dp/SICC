import controller.*;
import model.*;
import persistence.*;

public class Program {

    public static void main(String[] args) {

        DataManager<User> userManager = new DataManager<>(User.class);
        userManager.create(new UserAdmin("admin", "12345", "TESTE", "TESTADOR"));

        AppController controller = new AppController();
        controller.start();

    }

}