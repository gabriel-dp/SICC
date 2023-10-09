package src.view.services.admin;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.controller.AppController;
import src.model.RoleUser;
import src.model.User;
import src.model.UserStudent;
import src.model.Subject;

public class AdminPanelCurriculum extends JPanel {

    private JList<UserStudent> studentsList = new JList<>();
    private DefaultListModel<UserStudent> modelList = new DefaultListModel<>();

    public AdminPanelCurriculum() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalStrut(15)); // Margin
        mainPanel.add(createTitleLabel());
        mainPanel.add(Box.createVerticalStrut(15)); // Margin
        mainPanel.add(createSubjectsTable());
        mainPanel.add(Box.createVerticalStrut(15)); // Margin
        mainPanel.add(createButtonsPanel());

        this.add(mainPanel);
    }

    private JLabel createTitleLabel() {
        JLabel labelTitle = new JLabel("Currículos");
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD, 20));
        return labelTitle;
    }

    private void loadData() {
        ArrayList<UserStudent> students = new ArrayList<>();
        for (User u : AppController.getControllerUser().getAllData()) {
            if (u.getRole() == RoleUser.STUDENT) {
                UserStudent student = (UserStudent) u;
                students.add(student);
            }
        }
        modelList.clear();
        modelList.addAll(students);
        studentsList.setModel(modelList);
    }

    private JScrollPane createSubjectsTable() {
        JScrollPane scrollable = new JScrollPane();
        scrollable.setPreferredSize(new Dimension(600, 300));

        // Allows multi-select elements on single click
        studentsList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                } else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        loadData();

        scrollable.setViewportView(studentsList);
        return scrollable;
    }

    private JPanel createButtonsPanel() {
        JPanel thisPanel = this;

        JButton confirmButton = new JButton("Gerar relatório");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                // Set file extension filter to allow only .txt files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files (.txt)", "txt");
                fileChooser.setFileFilter(filter);

                if (fileChooser.showSaveDialog(thisPanel) == JFileChooser.APPROVE_OPTION) {
                    try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile().getAbsolutePath())) {
                        writer.write(generateReport());
                    } catch (IOException ex) {
                        System.out.println("Teste");
                    }
                }
            }
        });

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentsList.clearSelection();
            }
        });

        JButton reloadButton = new JButton("Atualizar");
        reloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        final JButton buttons[] = { reloadButton, cancelButton, confirmButton };
        JPanel buttonsPanel = new JPanel(new GridLayout(1, buttons.length, 15, 0));
        for (JButton b : buttons) {
            buttonsPanel.add(b);
        }

        return buttonsPanel;

    }

    private String generateReport() {
        StringBuilder sb = new StringBuilder();

        for (UserStudent us : studentsList.getSelectedValuesList()) {
            String name = us.getFullName();
            String username = us.getUsername();
            String course = us.getCourse().getCode();
            String date = us.getCurriculum().getFormattedLastEdit();
            sb.append(String.format("%s (%s) | %s | Data da solicitação: %s\n", name, username, course, date));
            sb.append("Currículo: {\n");
            for (Subject s : us.getCurriculum().getSubjects()) {
                sb.append("\t" + s.toString() + "\n");
            }
            sb.append("}\n\n");
        }

        return sb.toString();
    }

}
