package src.view.entity;

import javax.swing.*;

import src.model.Curriculum;

public class CurriculumServices extends EntityServices<Curriculum> {

    public CurriculumServices() {
        super("Currículos", Curriculum.class);

        JLabel label = new JLabel("Currículo");
        this.add(label);
    }

}
