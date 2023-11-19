package src.view.services.admin;

import java.util.ArrayList;
import javax.swing.*;

import src.controller.AppController;
import src.model.Professor;
import src.model.Subject;
import src.utils.text.*;

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

    protected void checkForm() throws InvalidInputException {
        new FormatTrim(new TextFormatterJText(tfName)).check();
        new FormatTrim(new TextFormatterJText(tfCode)).check();
        new FormatOnlyNumbers(new TextFormatterJText(tfHours)).check();
        new FormatOnlyNumbers(new TextFormatterJText(tfVacancies)).check();
    }

    protected Subject createEntity() {
        String name = new FormatTrim(new TextFormatterJText(tfName)).getText();
        String code = new FormatUppercase(new FormatTrim(new TextFormatterJText(tfCode))).getText();
        int hours = Integer.parseInt(tfHours.getText());
        int vacancies = Integer.parseInt(tfVacancies.getText());
        Professor professor = (Professor) cbProfessors.getSelectedItem();

        return new Subject(code, name, hours, vacancies, professor);
    }

}
