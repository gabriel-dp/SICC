package src.view.entity;

import javax.swing.*;

import src.model.Subject;

public class SubjectPanelAdmin extends EntityPanelAdmin<Subject> {

    public SubjectPanelAdmin() {
        super("Disciplinas", Subject.class);

        JLabel label = new JLabel("Disciplina");
        this.add(label);
    }

}
