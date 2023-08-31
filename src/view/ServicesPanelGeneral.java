package src.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.controller.AppController;
import src.model.User;

public class ServicesPanelGeneral extends JPanel {

    public ServicesPanelGeneral() {
        this.setLayout(new BorderLayout());

        // Sets user data to be displayed in general panel
        User user = AppController.getUserAuthenticated();
        String userFullName = user.getFirstName() + ' ' + user.getLastName();
        String userRole;
        switch (user.getRole()) {
            case ADMIN:
                this.add(new ServicesPanelAdmin(), BorderLayout.CENTER);
                userRole = "Administrador";
                break;
            case STUDENT:
                this.add(new ServicesPanelStudent(), BorderLayout.CENTER);
                userRole = "Estudante";
                break;
            default:
                userRole = "Cargo desconhecido";
                break;
        }

        this.add(createGeneralPanel(userFullName, userRole), BorderLayout.PAGE_END);
    }

    private JPanel createGeneralPanel(String userFullName, String userRole) {
        JPanel generalPanel = new JPanel(new BorderLayout());
        generalPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        generalPanel.setBackground(new Color(10, 200, 150));

        JLabel labelUser = new JLabel(String.format("%s (%s)", userFullName, userRole));
        generalPanel.add(labelUser, BorderLayout.CENTER);

        JButton buttonLogout = new JButton("Sair");
        buttonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogoutButtonClick();
            }
        });
        generalPanel.add(buttonLogout, BorderLayout.LINE_END);

        return generalPanel;
    }

    private void handleLogoutButtonClick() {
        logout();
    }

    private void logout() {
        AppController.logout();
        AppView.getInstance().showAuth();
    }

}
