package src.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.view.entity.*;

public class ServicesPanelAdmin extends JPanel {

    private CardLayout mainLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(mainLayout);

    private final EntityServices services[] = {
            // new UserServices(),
            // new CurriculumServices(),
            // new CourseServices(),
            // new SubjectServices(),
            new ProfessorServices()
    };

    public ServicesPanelAdmin() {
        this.setLayout(new BorderLayout());

        JPanel navPanel = new JPanel(new GridLayout(1, services.length));
        for (EntityServices s : services) {
            navPanel.add(createNavButton(s.getTitle()));
            mainPanel.add(s, s.getTitle());
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
