package src.view.services.student;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.controller.AppController;
import src.controller.EntityAlreadyExistsException;
import src.model.Subject;
import src.model.UserStudent;

public class StudentPanelSubjects extends JPanel {

    private UserStudent student;

    private JList<Subject> subjectList = new JList<>(),
            previousSubjectList = new JList<>();
    private DefaultListModel<Subject> modelList = new DefaultListModel<>(),
            previousModelList = new DefaultListModel<>();

    private JLabel lastEditLabel = new JLabel();

    public StudentPanelSubjects(UserStudent student) {
        this.student = student;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(15)); // Margin
        this.add(createTitleLabel("Solicitação de Disciplinas"));
        this.add(Box.createVerticalStrut(20)); // Margin
        this.add(createSubjectsTable());
        this.add(Box.createVerticalStrut(15)); // Margin
        this.add(createButtonsPanel());
        this.add(Box.createVerticalStrut(20)); // Margin
        this.add(lastEditLabel);
        updateLastEditLabel();
    }

    private JLabel createTitleLabel(String title) {
        JLabel labelTitle = new JLabel(title);
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD, 20));
        return labelTitle;
    }

    private JScrollPane createSubjectsTable() {
        JScrollPane scrollable = new JScrollPane();
        scrollable.setPreferredSize(new Dimension(600, 300));

        // Create Subject List
        modelList.clear();
        modelList.addAll(student.getCourse().getSubjects());
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

        scrollable.setViewportView(subjectList);
        return scrollable;
    }

    private void replaceCurriculum(ArrayList<Subject> newSubjects) {
        try {
            AppController.getControllerUser().delete(student.getId());
            student.getCurriculum().setSubjects(newSubjects);
            AppController.getControllerUser().create(student);
        } catch (EntityAlreadyExistsException ex) {
            System.out.println("Cannot replace curriculum");
        }
    }

    private JPanel createButtonsPanel() {
        JPanel thisPanel = this;

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Subject> newSubjects = new ArrayList<>(subjectList.getSelectedValuesList());
                replaceCurriculum(newSubjects);
                updateLastEditLabel();
                JOptionPane.showMessageDialog(thisPanel, "Solicitação efetuada", "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                subjectList.clearSelection();
            }
        });

        JButton moreButton = new JButton("Ver solicitação anterior");
        moreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Window actualWindow = javax.swing.FocusManager.getCurrentManager().getActiveWindow();
                JDialog subjectsMenu = new JDialog(actualWindow, "Última solicitação");
                subjectsMenu.setSize(500, 300);
                subjectsMenu.setModal(true);
                subjectsMenu.setLocationRelativeTo(actualWindow);
                subjectsMenu.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                subjectsMenu.getRootPane().setBorder(new EmptyBorder(15, 15, 15, 15));

                // Create Subject List
                previousModelList.clear();
                previousModelList.addAll(student.getCurriculum().getSubjects());
                previousSubjectList.setModel(previousModelList);

                subjectsMenu.add(new JScrollPane(previousSubjectList));
                subjectsMenu.setVisible(true);
            }
        });

        final JButton buttons[] = { moreButton, cancelButton, confirmButton };
        JPanel buttonsPanel = new JPanel(new GridLayout(1, buttons.length, 15, 0));
        for (JButton b : buttons) {
            buttonsPanel.add(b);
        }

        return buttonsPanel;

    }

    private JLabel updateLastEditLabel() {
        String message = String.format("Última solicitação registrada em: %s",
                student.getCurriculum().getFormattedLastEdit());
        lastEditLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lastEditLabel.setText(message);

        return lastEditLabel;
    }

}
