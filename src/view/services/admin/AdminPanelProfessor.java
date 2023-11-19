package src.view.services.admin;

import java.util.ArrayList;

import javax.swing.*;

import src.controller.AppController;
import src.model.Professor;
import src.utils.text.*;

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

    protected void checkForm() throws InvalidInputException {
        new FormatTrim(new TextFormatterJText(tfFirstName)).check();
        new FormatTrim(new TextFormatterJText(tfLastName)).check();
        new FormatEmail(new FormatTrim(new TextFormatterJText(tfEmail))).check();
    }

    protected Professor createEntity() {
        String firstName = new FormatTrim(new TextFormatterJText(tfFirstName)).getText();
        String lastName = new FormatTrim(new TextFormatterJText(tfLastName)).getText();
        String email = new FormatEmail(new FormatTrim(new TextFormatterJText(tfEmail))).getText();

        return new Professor(firstName, lastName, email);
    }

}
