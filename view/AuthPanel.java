package view;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.*;
import javax.swing.*;

import controller.AppController;

public class AuthPanel extends JPanel {

    private JTextField tfUser;
    private JPasswordField pwfUser;

    public AuthPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Fazer login");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(330, 50, 150, 30);
        this.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Usuário");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(250, 160, 74, 14);
        this.add(lblNewLabel_1);

        tfUser = new JTextField();
        tfUser.setBackground(SystemColor.inactiveCaption);
		tfUser.setBounds(330, 150, 190, 30);
        tfUser.setColumns(10);
        this.add(tfUser);

        JLabel lblNewLabel_1_1 = new JLabel("Senha");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(250, 250, 74, 14);
        this.add(lblNewLabel_1_1);

        pwfUser = new JPasswordField();
        pwfUser.setEchoChar('*');
        pwfUser.setBackground(SystemColor.inactiveCaption);
		pwfUser.setBounds(330, 240, 190, 30);
        pwfUser.setToolTipText("");
        pwfUser.setColumns(10);
        this.add(pwfUser);

        JButton btnLogin = new JButton("Entrar na conta");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLoginButtonClick();
            }
        });
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnLogin.setBounds(310, 330, 190, 31);
        btnLogin.setBackground(SystemColor.LIGHT_GRAY);
        this.add(btnLogin);
    }

    public void handleLoginButtonClick() {
        String username = tfUser.getText();
        String password = new String(pwfUser.getPassword());

        // More username and password verifications

        attemptLogin(username, password);
    }

    private void attemptLogin(String username, String password) {
        if (AppController.getInstance().login(username, password)) {
            AppView.getInstance().showServices();
        } else {
            JOptionPane.showMessageDialog(null, "Falha no login. Verifique suas credenciais.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

}
