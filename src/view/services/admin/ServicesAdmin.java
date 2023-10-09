package src.view.services.admin;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;

public class ServicesAdmin extends JPanel {

    private CardLayout mainLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(mainLayout);

    private Map<JPanel, String> services = new LinkedHashMap<JPanel, String>() {
        {
            put(new AdminPanelUser(), "Usuários");
            put(new AdminPanelCourse(), "Cursos");
            put(new AdminPanelSubject(), "Disciplinas");
            put(new AdminPanelProfessor(), "Professores");
        }
    };

    public ServicesAdmin() {
        this.setLayout(new BorderLayout());

        JPanel navPanel = new JPanel(new GridLayout(1, services.size()));
        for (Map.Entry<JPanel, String> s : services.entrySet()) {
            navPanel.add(createNavButton(s.getValue()));
            mainPanel.add(s.getKey(), s.getValue());
        }

        this.add(navPanel, BorderLayout.PAGE_START);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    // Creates a main button with the event to redirects to the desired panel
    private JButton createNavButton(String title) {
        JButton button = new JButton(title);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainLayout.show(mainPanel, title);
            }
        });

        return button;
    }

}
