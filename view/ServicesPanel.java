package view;

import java.awt.event.*;
import javax.swing.*;

public class ServicesPanel extends AppPanel {

    public ServicesPanel(AppFrame frame) {
        super(frame);

        JLabel lblNewLabel = new JLabel(String.format("Welcome - %s", frame.getController().getUserAuthenticated()));
        this.add(lblNewLabel);

        JButton btnLogin = new JButton("Sair");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogoutButtonClick();
            }
        });
        this.add(btnLogin);
    }

    private void handleLogoutButtonClick() {
        logout();
    }

    private void logout() {
        frame.getController().logout();
        frame.showAuth();
    }

}
