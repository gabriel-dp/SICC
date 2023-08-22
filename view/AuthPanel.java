package view;

import javax.swing.*;

import java.awt.event.*;

import controller.AppController;

public class AuthPanel extends JPanel {

    private JTextField tfUser;
    private JPasswordField pwfUser;

    public AuthPanel(AppFrame frame, AppController controller) {

        JLabel lblNewLabel = new JLabel("Fazer login");
        this.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Usuário");
        this.add(lblNewLabel_1);

        tfUser = new JTextField();
        tfUser.setColumns(10);
        this.add(tfUser);

        JLabel lblNewLabel_1_1 = new JLabel("Senha");
        this.add(lblNewLabel_1_1);

        pwfUser = new JPasswordField();
        pwfUser.setEchoChar('*');
        pwfUser.setToolTipText("");
        pwfUser.setColumns(10);
        this.add(pwfUser);

        JButton btnLogin = new JButton("Entrar na conta");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attemptLogin(frame, controller);
            }
        });
        this.add(btnLogin);

    }

    public void attemptLogin(AppFrame frame, AppController controller) {
        String username = tfUser.getText();
        String password = new String(pwfUser.getPassword());

        // More username and password verifications

        if (controller.login(username, password)) {
            frame.showPanel("services");
        } else {
            JOptionPane.showMessageDialog(null, "Falha no login. Verifique suas credenciais.");
        }
    }

}
