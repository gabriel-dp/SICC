package src.view.entity;

import javax.swing.JPanel;

public abstract class EntityServices extends JPanel {

    private String title;

    public EntityServices(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}