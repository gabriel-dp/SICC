package src.view.services;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.controller.AppController;
import src.model.User;
import src.model.UserStudent;
import src.view.AppView;
import src.view.services.admin.ServicesAdmin;
import src.view.services.student.ServicesStudent;

public class ServicesPanel extends JPanel {

    public ServicesPanel() {
        this.setLayout(new BorderLayout());

        // Sets user data to be displayed in general panel
        User user = AppController.getUserAuthenticated();
        String userRole;
        switch (user.getRole()) {
            case ADMIN:
                this.add(new ServicesAdmin(), BorderLayout.CENTER);
                userRole = "Administrador";
                break;
            case STUDENT:
                this.add(new ServicesStudent((UserStudent) user), BorderLayout.CENTER);
                userRole = "Estudante";
                break;
            default:
                userRole = "Cargo desconhecido";
                break;
        }

        this.add(createGeneralPanel(user.getFullName(), userRole), BorderLayout.PAGE_END);
    }

    private JPanel createGeneralPanel(String userFullName, String userRole) {
        JPanel generalPanel = new JPanel(new BorderLayout());
        generalPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        generalPanel.setBackground(new Color(193, 213, 232));

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
