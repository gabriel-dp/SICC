package src.view.services.student;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.model.UserStudent;

public class ServicesStudent extends JPanel {

    public ServicesStudent(UserStudent student) {
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(new StudentPanelSubjects(student));
    }

}
