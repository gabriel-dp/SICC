import controller.*;
import model.*;
import persistence.*;
import view.TelaLogin;

public class Program {

    public static void main(String[] args) {

        DataManager<User> userManager = new DataManager<>(User.class);
        userManager.create(new UserAdmin("teste1", "teste", "TESTE", "TESTADOR"));

        //UserAuthenticator auth = new UserAuthenticator();

        //User user1 = auth.authenticate("teste1", "teste");
        //User user2 = auth.authenticate("teste", "testando");

        //System.out.println(user1);
        //System.out.println(user2);

        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);

    }

}