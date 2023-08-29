package src.view.entity;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import src.controller.DataController;
import src.model.Course;
import src.model.User;
import src.model.UserAdmin;
import src.model.UserStudent;

public class UserPanelAdmin extends EntityPanelAdmin<User> {

    private static JTextField tfUsername = new JTextField(),
            tfPassword = new JTextField(),
            tfFirstName = new JTextField(),
            tfLastName = new JTextField();

    private static JRadioButton rbAdmin = new JRadioButton(),
            rbStudent = new JRadioButton();

    private static JComboBox<Object> cbCourses = new JComboBox<>();

    public UserPanelAdmin() {
        super("Usuários", User.class);
    }

    protected void defineFormPanel() {
        addTextInput(tfUsername, "Usuário", 0, 0, 1);
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

        // Add all Courses in the ComboBox
        cbCourses.addItem("Selecione um curso");
        for (Object c : new DataController<Course>(Course.class).getAllData()) {
            cbCourses.addItem(c);
        }

        // Event makes JComboBox enabled only if Student button is selected
        cbCourses.setEnabled(false);
        rbStudent.addItemListener(e -> {
            cbCourses.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
            if (!cbCourses.isEnabled())
                cbCourses.setSelectedIndex(0);
        });
        addComboBoxInput(cbCourses, "Curso (Estudante)", 1, 2, 1);
    }

    protected void defineTable() {
        tableModel.addColumn("Id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Usuário");
        tableModel.addColumn("Senha");
        tableModel.addColumn("Cargo");
    }

    protected ArrayList<Object[]> getTableData(DataController<User> dc) {
        ArrayList<Object[]> data = new ArrayList<>();

        for (User u : dc.getAllData()) {
            String name = u.getFirstName() + ' ' + u.getLastName();
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

            Object[] row = { u.getId(), name, u.getUsername(), u.getPassword(), role };
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

    protected void checkForm() throws Exception {
        if (tfUsername.getText().isBlank()
                || tfPassword.getText().isBlank()
                || tfFirstName.getText().isBlank()
                || tfLastName.getText().isBlank()
                || (!rbAdmin.isSelected() && !rbStudent.isSelected())
                || (rbStudent.isSelected() && cbCourses.getSelectedIndex() == 1)) {
            throw new Exception("Campos inválidos");
        }

    }

    protected User createEntity() {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();

        if (rbStudent.isSelected()) {
            // Course course = (Course) cbCourses.getSelectedItem();
            // return new UserStudent(username, password, firstName, lastName, course);
            return new UserStudent(username, password, firstName, lastName, null);
        }
        return new UserAdmin(username, password, firstName, lastName);
    }

}
