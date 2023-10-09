package src.view.services.admin;

import java.util.ArrayList;

import javax.swing.*;

import src.controller.AppController;
import src.model.Professor;
import src.view.services.InvalidInputsException;

public class AdminPanelProfessor extends EntityPanel<Professor> {

    private static JTextField tfFirstName = new JTextField(),
            tfLastName = new JTextField(),
            tfEmail = new JTextField();

    public AdminPanelProfessor() {
        super("Professores", AppController.getControllerProfessor());
    }

    protected void defineFormPanel() {
        addTextInput(tfFirstName, "Primeiro nome", 0, 0, 1);
        addTextInput(tfLastName, "Último nome", 1, 0, 1);
        addTextInput(tfEmail, "Email*", 0, 1, 2);
    }

    protected void defineTable() {
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
    }

    protected ArrayList<Object[]> getTableData() {
        ArrayList<Object[]> data = new ArrayList<>();

        for (Professor p : dataController.getAllData()) {
            Object row[] = { p.getId(), p.getFullName(), p.getEmail() };
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
