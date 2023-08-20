import control.*;
import model.*;
// import persistence.*;

public class Program {

    public static void main(String[] args) {

        UserAuthenticator auth = new UserAuthenticator();

        User user1 = auth.authenticate("teste1", "teste");
        User user2 = auth.authenticate("teste", "testando");

        System.out.println(user1);
        System.out.println(user2);

    }

}