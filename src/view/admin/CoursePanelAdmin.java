package src.view.admin;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.controller.AppController;
import src.model.Course;
import src.model.Subject;

public class CoursePanelAdmin extends EntityPanelAdmin<Course> {

    private static JTextField tfCode = new JTextField(),
            tfName = new JTextField(),
            tfSemesters = new JTextField();

    private static JComboBox<Object> cbType = new JComboBox<>(),
            cbShift = new JComboBox<>();

    private static JButton btSubjects = new JButton();
    private static JList<Subject> subjectList = new JList<>();
    private static DefaultListModel<Subject> modelList = new DefaultListModel<>();

    public CoursePanelAdmin() {
        super("Cursos", AppController.getControllerCourse());
    }

    protected void defineFormPanel() {
        addTextInput(tfCode, "Código*", 0, 0, 1);
        addTextInput(tfName, "Nome", 1, 0, 1);

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

        addTextInput(tfSemesters, "Semestres", 0, 2, 1);

        btSubjects.setText("Selecionar Disciplinas");
        btSubjects.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Creates dialog window
                Window actualWindow = javax.swing.FocusManager.getCurrentManager().getActiveWindow();
                JDialog subjectsMenu = new JDialog(actualWindow, "Adicionar Disciplinas");
                subjectsMenu.setSize(500, 300);
                subjectsMenu.setModal(true);
                subjectsMenu.setLocationRelativeTo(actualWindow);
                subjectsMenu.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                subjectsMenu.getRootPane().setBorder(new EmptyBorder(15, 15, 15, 15));

                // Create Subject List
                modelList.clear();
                modelList.addAll(AppController.getControllerSubject().getAllData());
                subjectList.setModel(modelList);

                // Allows multi-select elements on single click
                subjectList.setSelectionModel(new DefaultListSelectionModel() {
                    @Override
                    public void setSelectionInterval(int index0, int index1) {
                        if (super.isSelectedIndex(index0)) {
                            super.removeSelectionInterval(index0, index1);
                        } else {
                            super.addSelectionInterval(index0, index1);
                        }
                    }
                });

                // Makes list scrollable and add it to main panel
                subjectsMenu.add(new JScrollPane(subjectList));
                subjectsMenu.setVisible(true);
            }
        });
        addButtonInput(btSubjects, "Disciplinas", 1, 2, 1);
    }

    protected void defineTable() {
        tableModel.addColumn("Código");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Turno");
        tableModel.addColumn("Semestres");
        tableModel.addColumn("Disciplinas");
    }

    protected ArrayList<Object[]> getTableData() {
        ArrayList<Object[]> data = new ArrayList<>();

        for (Course c : dataController.getAllData()) {
            Object[] row = {
                    c.getId(),
                    c.getCode(),
                    c.getName(),
                    c.getType(),
                    c.getShift(),
                    c.getSemesters(),
                    c.getSubjects().size()
            };
            data.add(row);
        }

        return data;
    }

    protected void clearForm() {
        tfCode.setText("");
        tfName.setText("");
        cbType.setSelectedIndex(0);
        cbShift.setSelectedIndex(0);
        tfSemesters.setText("");
        subjectList.clearSelection();
    }

    protected void checkForm() throws InvalidInputsException {
        try {
            int semestersInt = Integer.parseInt(tfSemesters.getText());
            if (tfCode.getText().isBlank()
                    || tfName.getText().isBlank()
                    || semestersInt <= 0
                    || subjectList.getSelectedValuesList().size() == 0) {
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
        List<Subject> subjects = subjectList.getSelectedValuesList();

        return new Course(code, name, type, shift, semesters, new ArrayList<>(subjects));
    }

}
