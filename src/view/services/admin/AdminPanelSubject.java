package src.view.services.admin;

import java.util.ArrayList;
import javax.swing.*;

import src.controller.AppController;
import src.model.Professor;
import src.model.Subject;
import src.view.services.InvalidInputsException;

public class AdminPanelSubject extends EntityPanel<Subject> {

    private static JTextField tfCode = new JTextField(),
            tfName = new JTextField(),
            tfHours = new JTextField(),
            tfVacancies = new JTextField();

    private static JComboBox<Object> cbProfessors = new JComboBox<>();

    public AdminPanelSubject() {
        super("Disciplinas", AppController.getControllerSubject());
    }

    protected void defineFormPanel() {
        addTextInput(tfName, "Nome", 0, 0, 1);
        addTextInput(tfCode, "Código*", 1, 0, 1);
        addTextInput(tfHours, "Horas", 0, 1, 1);
        addTextInput(tfVacancies, "Vagas", 1, 1, 1);

        addComboBoxInput(cbProfessors, "Professor", "Selecione um professor", 0, 2, 2);
        setComboBoxEntityListener(cbProfessors, AppController.getControllerProfessor());
    }

    protected void defineTable() {
        tableModel.addColumn("Nome");
        tableModel.addColumn("Código");
        tableModel.addColumn("Horas");
        tableModel.addColumn("Vagas");
        tableModel.addColumn("Professor");
    }

    protected ArrayList<Object[]> getTableData() {
        ArrayList<Object[]> data = new ArrayList<>();

        for (Subject s : dataController.getAllData()) {
            int vacancies = s.getTotalVacancies();
            String professor = String.format("%s (%s)", s.getProfessor().getFullName(), s.getProfessor().getEmail());

            Object[] row = { s.getId(), s.getName(), s.getCode(), s.getHours(), vacancies, professor };
            data.add(row);
        }

        return data;
    }

    protected void clearForm() {
        tfName.setText("");
        tfCode.setText("");
        tfHours.setText("");
        tfVacancies.setText("");
        cbProfessors.setSelectedIndex(0);
    }

    protected void checkForm() throws InvalidInputsException {
        try {
            int hoursInt = Integer.parseInt(tfHours.getText());
            int vacanciesInt = Integer.parseInt(tfHours.getText());
            if (tfName.getText().isBlank()
                    || tfCode.getText().isBlank()
                    || tfHours.getText().isBlank()
                    || tfVacancies.getText().isBlank()
                    || cbProfessors.getSelectedIndex() == 0
                    || hoursInt < 0
                    || vacanciesInt < 0) {
                throw new InvalidInputsException();
            }
        } catch (NumberFormatException ex) {
            throw new InvalidInputsException();
        }
    }

    protected Subject createEntity() {
        String name = tfName.getText();
        String code = tfCode.getText();
        int hours = Integer.parseInt(tfHours.getText());
        int vacancies = Integer.parseInt(tfVacancies.getText());
        Professor professor = (Professor) cbProfessors.getSelectedItem();

        return new Subject(code, name, hours, vacancies, professor);
    }

}
