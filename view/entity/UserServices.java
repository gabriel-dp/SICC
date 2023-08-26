package view.entity;

import javax.swing.*;

public class UserServices extends EntityServices {

    public UserServices() {
        super("Usuários");

        JLabel label = new JLabel("Usuário");
        this.add(label);
    }

}
