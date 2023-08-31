package src.view.admin;

import java.util.ArrayList;

import javax.swing.*;

import src.controller.AppController;
import src.model.Professor;

public class ProfessorPanelAdmin extends EntityPanelAdmin<Professor> {

    private static JTextField tfFirstName = new JTextField(),
            tfLastName = new JTextField(),
            tfEmail = new JTextField();

    public ProfessorPanelAdmin() {
        super("Professores", AppController.getControllerProfessor());
    }

    protected void defineFormPanel() {
        addTextInput(tfFirstName, "Primeiro nome", 0, 0, 1);
        addTextInput(tfLastName, "Último nome", 1, 0, 1);
        addTextInput(tfEmail, "Email*", 0, 1, 2);
    }

    protected void defineTable() {
        tableModel.addColumn("Id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");

        // Turn Id column invisible
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    protected ArrayList<Object[]> getTableData() {
        ArrayList<Object[]> data = new ArrayList<>();

        for (Professor p : dataController.getAllData()) {
            String name = p.getFirstName() + ' ' + p.getLastName();
            Object row[] = { p.getId(), name, p.getEmail() };
            data.add(row);
        }

        return data;
    }

    protected void clearForm() {
        tfFirstName.setText("");
        tfLastName.setText("");
        tfEmail.setText("");
    }

    protected void checkForm() throws InvalidInputsException {
        if (tfFirstName.getText().isBlank()
                || tfLastName.getText().isBlank()
                || tfEmail.getText().isBlank()) {
            throw new InvalidInputsException();
        }
    }

    protected Professor createEntity() {
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String email = tfEmail.getText();

        return new Professor(firstName, lastName, email);
    }

}
