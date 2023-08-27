package src.view.entity;

import javax.swing.*;

import src.controller.DataController;
import src.model.Professor;

public class ProfessorServices extends EntityServices {

    private static JTextField tfFirstName = new JTextField();
    private static JTextField tfLastName = new JTextField();
    private static JTextField tfEmail = new JTextField();

    public ProfessorServices() {
        super("Professores");
    }

    protected void defineFormPanel() {
        addTextInput(tfFirstName, "Primeiro nome", 0, 0, 1);
        addTextInput(tfLastName, "Último nome", 1, 0, 1);
        addTextInput(tfEmail, "Email", 0, 1, 2);
    }

    protected void defineTable() {
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
    }

    protected void loadTableData() {
        // Resets table data
        tableModel.setNumRows(0);

        DataController<Professor> dc = new DataController<>(Professor.class);
        for (Professor p : dc.getAllData()) {
            String name = p.getFirstName() + ' ' + p.getLastName();
            Object data[] = { name, p.getEmail() };
            tableModel.addRow(data);
        }

    }

}
