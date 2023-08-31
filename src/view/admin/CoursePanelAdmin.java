package src.view.admin;

import javax.swing.*;

import src.model.Course;

public class CoursePanelAdmin extends EntityPanelAdmin<Course> {

    public CoursePanelAdmin() {
        super("Cursos", Course.class);
    }

}
