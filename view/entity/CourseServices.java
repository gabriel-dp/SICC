package view.entity;

import javax.swing.*;

public class CourseServices extends EntityServices {

    public CourseServices() {
        super("Cursos");

        JLabel label = new JLabel("Curso");
        this.add(label);
    }

}
