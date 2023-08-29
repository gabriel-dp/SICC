package src.view.entity;

import javax.swing.*;

import src.model.Subject;

public class SubjectServices extends EntityServices<Subject> {

    public SubjectServices() {
        super("Disciplinas", Subject.class);

        JLabel label = new JLabel("Disciplina");
        this.add(label);
    }

}
