package src.view.admin;

import java.util.ArrayList;

import javax.swing.*;

import src.controller.AppController;
import src.model.Course;

public class CoursePanelAdmin extends EntityPanelAdmin<Course> {

    private static JTextField tfCode = new JTextField(),
            tfName = new JTextField(),
            tfSemesters = new JTextField();

    private static JComboBox<Object> cbType = new JComboBox<>(),
            cbShift = new JComboBox<>();

    public CoursePanelAdmin() {
        super("Cursos", AppController.getControllerCourse());
    }

    protected void defineFormPanel() {
        addTextInput(tfCode, "Código*", 0, 0, 1);
        addTextInput(tfName, "Nome", 1, 0, 1);
        addTextInput(tfSemesters, "Semestres", 0, 2, 1);

        final String[] types = { "Bacharelado", "Licenciatura" };
        cbType.setModel(new DefaultComboBoxModel<>(types));
        addComboBoxInput(cbType, "Tipo", null, 0, 1, 1);

        final String[] shifts = { "Matutino",
                "Vespertino",
                "Noturno",
                "Integral (M/V)",
                "Integral (V/N)" };
        cbShift.setModel(new DefaultComboBoxModel<>(shifts));
        addComboBoxInput(cbShift, "Turno", null, 1, 1, 1);
    }

    protected void defineTable() {
        tableModel.addColumn("Código");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Turno");
        tableModel.addColumn("Semestres");
    }

    protected ArrayList<Object[]> getTableData() {
        ArrayList<Object[]> data = new ArrayList<>();

        for (Course c : dataController.getAllData()) {
            Object[] row = { c.getId(), c.getCode(), c.getName(), c.getType(), c.getShift(), c.getSemesters() };
            data.add(row);
        }

        return data;
    }

    protected void clearForm() {
        tfCode.setText("");
        tfName.setText("");
        tfSemesters.setText("");
    }

    protected void checkForm() throws InvalidInputsException {
        try {
            int semestersInt = Integer.parseInt(tfSemesters.getText());
            if (tfCode.getText().isBlank()
                    || tfName.getText().isBlank()
                    || semestersInt <= 0) {
                throw new InvalidInputsException();
            }
        } catch (NumberFormatException ex) {
            throw new InvalidInputsException();
        }
    }

    protected Course createEntity() {
        String code = tfCode.getText();
        String name = tfName.getText();
        int semesters = Integer.parseInt(tfSemesters.getText());
        String type = (String) cbType.getSelectedItem();
        String shift = (String) cbShift.getSelectedItem();

        return new Course(code, name, type, shift, semesters, null);
    }

}
