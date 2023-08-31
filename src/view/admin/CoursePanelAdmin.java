package src.view.admin;

import javax.swing.*;

import src.controller.AppController;
import src.model.Course;

public class CoursePanelAdmin extends EntityPanelAdmin<Course> {

    public CoursePanelAdmin() {
        super("Cursos", AppController.getControllerCourse());
    }

}
