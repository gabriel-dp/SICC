package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserAuthenticator;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField pwfUser;

	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 450, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 414, 259);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fazer login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(157, 17, 97, 25);
		panel.add(lblNewLabel);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsurio.setBounds(128, 59, 74, 14);
		panel.add(lblUsurio);
		
		tfUser = new JTextField();
		tfUser.setBackground(SystemColor.inactiveCaption);
		tfUser.setBounds(128, 84, 168, 29);
		panel.add(tfUser);
		tfUser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(128, 130, 46, 14);
		panel.add(lblNewLabel_1);
		
		pwfUser = new JPasswordField();
		pwfUser.setEchoChar('*');
		pwfUser.setBackground(SystemColor.inactiveCaption);
		pwfUser.setBounds(128, 155, 168, 32);
		panel.add(pwfUser);
		
		JButton btnLogin = new JButton("Entrar na conta");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfUser.getText();
		        char[] passwordChars = pwfUser.getPassword();
		        String password = new String(passwordChars);

		        UserAuthenticator auth = new UserAuthenticator();
		        User authenticatedUser = auth.authenticate(username, password);
                System.out.println(authenticatedUser);

		        if (authenticatedUser != null) {
		            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
		        } else {
		            JOptionPane.showMessageDialog(null, "Falha no login. Verifique suas credenciais.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLogin.setBounds(128, 217, 155, 31);
		panel.add(btnLogin);
	}
}
