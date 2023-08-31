package src.view.admin;

import javax.swing.*;

import src.controller.AppController;
import src.model.Subject;

public class SubjectPanelAdmin extends EntityPanelAdmin<Subject> {

    public SubjectPanelAdmin() {
        super("Disciplinas", AppController.getControllerSubject());

        JLabel label = new JLabel("Disciplina");
        this.add(label);
    }

}
