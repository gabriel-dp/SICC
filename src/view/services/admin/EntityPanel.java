package src.view.services.admin;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import src.controller.*;
import src.model.Entity;
import src.view.services.InvalidInputsException;

public abstract class EntityPanel<T extends Entity> extends JPanel {

    private String title;
    protected DataController<T> dataController;

    private GridBagLayout formGridBagLayout = new GridBagLayout();
    private JPanel formPanel = new JPanel(formGridBagLayout);

    protected DefaultTableModel tableModel = new DefaultTableModel();
    protected JTable table = new JTable(tableModel);

    public EntityPanel(String title, DataController<T> dataController) {
        this.title = title;
        this.dataController = dataController;

        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(createTitleLabel()); // Title
        mainPanel.add(Box.createVerticalStrut(15)); // Margin
        defineFormPanel(); // Form
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(15)); // Margin
        mainPanel.add(createActionButtons()); // Buttons
        mainPanel.add(Box.createVerticalStrut(15)); // Margin
        mainPanel.add(createScrollTable()); // Table
        loadTable();

        this.add(mainPanel);
    }

    protected abstract void defineFormPanel();

    protected abstract void clearForm();

    protected abstract void checkForm() throws InvalidInputsException;

    protected abstract T createEntity();

    protected abstract void defineTable();

    protected abstract ArrayList<Object[]> getTableData();

    public String getTitle() {
        return title;
    }

    private JLabel createTitleLabel() {
        JLabel labelTitle = new JLabel(title);
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD, 20));
        return labelTitle;
    }

    private void addFormPanelGrid(JComponent component, int x, int y, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.fill = GridBagConstraints.BOTH;
        formGridBagLayout.setConstraints(component, gbc);

        formPanel.add(component);
    }

    protected void addTextInput(JTextField textField, String label, int x, int y, int width) {
        JPanel subpanel = new JPanel(new FlowLayout());
        subpanel.setBorder(BorderFactory.createTitledBorder(label));

        int textFieldColumns = (15 * width) + (2 * (width - 1));
        textField.setColumns(textFieldColumns);

        subpanel.add(textField);
        addFormPanelGrid(subpanel, x, y, width);
    }

    protected void addButtonInput(JButton button, String label, int x, int y, int width) {
        JPanel subpanel = new JPanel(new FlowLayout());
        subpanel.setBorder(BorderFactory.createTitledBorder(label));

        subpanel.add(button);
        addFormPanelGrid(subpanel, x, y, width);
    }

    protected void addRadioButtonsInput(ButtonGroup radioButtons, String label, int x, int y, int width) {
        JPanel subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.Y_AXIS));
        subpanel.setBorder(BorderFactory.createTitledBorder(label));

        Enumeration<AbstractButton> buttons = radioButtons.getElements();
        while (buttons.hasMoreElements()) {
            JRadioButton button = (JRadioButton) buttons.nextElement();
            subpanel.add(button);
        }

        addFormPanelGrid(subpanel, x, y, width);
    }

    protected void addComboBoxInput(JComboBox<Object> comboBox, String name, String first, int x, int y, int width) {
        JPanel subpanel = new JPanel(new FlowLayout());
        subpanel.setBorder(BorderFactory.createTitledBorder(name));
        if (first != null) {
            comboBox.addItem(first);
        }

        // Set max width
        comboBox.setPrototypeDisplayValue("a".repeat(15 * width + 6 * (width - 1)));

        subpanel.add(comboBox);
        addFormPanelGrid(subpanel, x, y, width);
    }

    protected void setComboBoxEntityListener(JComboBox<Object> comboBox, DataController<?> controller) {
        // Sets event to load data on click
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                String first = (String) comboBox.getItemAt(0);
                comboBox.setModel(new DefaultComboBoxModel<>(controller.getAllData().toArray()));
                comboBox.insertItemAt(first, 0);
                comboBox.setSelectedIndex(0);
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }

    private JPanel createActionButtons() {
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    checkForm();
                    dataController.create(createEntity());
                    loadTable();
                    clearForm();
                } catch (InvalidInputsException ex) {
                    final String errorMessage = "Preencha todos os campos corretamente.";
                    JOptionPane.showMessageDialog(null, errorMessage, "Falha no cadastro", JOptionPane.WARNING_MESSAGE);
                } catch (EntityAlreadyExistsException ex) {
                    final String errorMessage = "Entidade já existente.";
                    JOptionPane.showMessageDialog(null, errorMessage, "Falha no cadastro", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton delButton = new JButton("Remover");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                if (index != -1) {
                    dataController.delete((String) table.getValueAt(index, 0));
                    loadTable();
                }
            }
        });

        final JButton buttons[] = { addButton, delButton };
        JPanel buttonsPanel = new JPanel(new GridLayout(1, buttons.length, 15, 0));
        for (JButton b : buttons) {
            buttonsPanel.add(b);
        }

        return buttonsPanel;
    }

    private JScrollPane createScrollTable() {
        tableModel.addColumn("Id");
        defineTable();

        // Turn Id column invisible
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        // Table propertiers
        table.setDefaultEditor(Object.class, null); // Disable cell editing
        table.getTableHeader().setReorderingAllowed(false); // Disable columns reorder
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Disable multi-select rows

        // Creates scrollable panel to contain table
        JScrollPane scrollable = new JScrollPane();
        scrollable.setPreferredSize(new Dimension(600, 180));
        scrollable.setViewportView(table);

        return scrollable;
    }

    private void loadTable() {
        // Resets table data
        tableModel.setNumRows(0);

        // Adds all rows to the table
        for (Object[] row : getTableData()) {
            tableModel.addRow(row);
        }
    }

}