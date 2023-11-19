package src.view.services.admin;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import src.controller.AppController;
import src.model.Course;
import src.model.User;
import src.model.UserAdmin;
import src.model.UserStudent;
import src.utils.text.*;

public class AdminPanelUser extends EntityPanel<User> {

    private static JTextField tfUsername = new JTextField(),
            tfPassword = new JTextField(),
            tfFirstName = new JTextField(),
            tfLastName = new JTextField();

    private static JRadioButton rbAdmin = new JRadioButton(),
            rbStudent = new JRadioButton();

    private static JComboBox<Object> cbCourses = new JComboBox<>();

    public AdminPanelUser() {
        super("Usuários", AppController.getControllerUser());
    }

    protected void defineFormPanel() {
        addTextInput(tfUsername, "Usuário*", 0, 0, 1);
        addTextInput(tfPassword, "Senha", 1, 0, 1);
        addTextInput(tfFirstName, "Primeiro nome", 0, 1, 1);
        addTextInput(tfLastName, "Último nome", 1, 1, 1);

        rbAdmin.setText("Administrador");
        rbStudent.setText("Estudante");
        rbAdmin.setSelected(true);

        // ButtonGroup forces just one JRadioButton be selected
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbAdmin);
        buttonGroup.add(rbStudent);
        addRadioButtonsInput(buttonGroup, "Cargo", 0, 2, 1);

        // Event makes JComboBox enabled only if Student button is selected
        cbCourses.setEnabled(false);
        rbStudent.addItemListener(e -> {
            cbCourses.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
            if (!cbCourses.isEnabled())
                cbCourses.setSelectedIndex(0);
        });

        addComboBoxInput(cbCourses, "Curso (Estudante)", "Selecione o curso", 1, 2, 1);
        setComboBoxEntityListener(cbCourses, AppController.getControllerCourse());
    }

    protected void defineTable() {
        tableModel.addColumn("Nome");
        tableModel.addColumn("Usuário");
        tableModel.addColumn("Senha");
        tableModel.addColumn("Cargo");
    }

    protected ArrayList<Object[]> getTableData() {
        ArrayList<Object[]> data = new ArrayList<>();

        for (User u : dataController.getAllData()) {
            String role;
            switch (u.getRole()) {
                case ADMIN:
                    role = "Administrador";
                    break;
                case STUDENT:
                    role = String.format("Estudante (%s)", ((UserStudent) u).getCourse());
                    break;
                default:
                    role = "-";
                    break;
            }

            Object[] row = { u.getId(), u.getFullName(), u.getUsername(), u.getPassword(), role };
            data.add(row);
        }

        return data;
    }

    protected void clearForm() {
        tfUsername.setText("");
        tfPassword.setText("");
        tfFirstName.setText("");
        tfLastName.setText("");
        rbAdmin.setSelected(true);
    }

    protected void checkForm() throws InvalidInputException {
        new FormatTrim(new TextFormatterJText(tfUsername)).check();
        new FormatPassword(new TextFormatterJText(tfPassword)).check();
        new FormatTrim(new TextFormatterJText(tfFirstName)).check();
        new FormatTrim(new TextFormatterJText(tfLastName)).check();

        if (rbStudent.isSelected() && cbCourses.getSelectedIndex() == 0) {
            throw new InvalidInputException();
        }
    }

    protected User createEntity() {
        String username = new FormatTrim(new TextFormatterJText(tfUsername)).getText();
        String password = new FormatTrim(new TextFormatterJText(tfPassword)).getText();
        String firstName = new FormatTrim(new TextFormatterJText(tfFirstName)).getText();
        String lastName = new FormatTrim(new TextFormatterJText(tfLastName)).getText();

        if (rbStudent.isSelected()) {
            Course course = (Course) cbCourses.getSelectedItem();
            return new UserStudent(username, password, firstName, lastName, course);
        }
        return new UserAdmin(username, password, firstName, lastName);
    }

}
