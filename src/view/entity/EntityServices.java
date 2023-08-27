package src.view.entity;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public abstract class EntityServices extends JPanel {

    private String title;

    private GridBagLayout formGridBagLayout = new GridBagLayout();
    protected JPanel formPanel = new JPanel(formGridBagLayout);

    protected DefaultTableModel tableModel = new DefaultTableModel();
    protected JTable table = new JTable(tableModel) {
        // Disable table edition by user
        public boolean isCellEditable(int row, int column) {
            return false;
        };
    };

    public EntityServices(String title) {
        this.title = title;
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title
        mainPanel.add(createTitleLabel());
        // Margin
        mainPanel.add(Box.createVerticalStrut(15));
        // Form
        defineFormPanel();
        mainPanel.add(formPanel);
        // Margin
        mainPanel.add(Box.createVerticalStrut(15));
        // Buttons
        mainPanel.add(createActionButtons());
        // Margin
        mainPanel.add(Box.createVerticalStrut(15));
        // Table
        mainPanel.add(createScrollTable());
        loadTableData();

        this.add(mainPanel);
    }

    protected abstract void defineFormPanel();

    protected abstract void defineTable();

    protected abstract void loadTableData();

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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formGridBagLayout.setConstraints(component, gbc);

        formPanel.add(component);
    }

    protected void addTextInput(JTextField tf, String name, int x, int y, int width) {
        JPanel subpanel = new JPanel(new FlowLayout());
        subpanel.setBorder(BorderFactory.createTitledBorder(name));

        tf.setColumns((15 * width) + (2 * (width - 1)));
        subpanel.add(tf);

        addFormPanelGrid(subpanel, x, y, width);
    }

    private JPanel createActionButtons() {
        JButton addButton = new JButton("Adicionar");
        JButton delButton = new JButton("Remover");

        JButton buttons[] = { addButton, delButton };
        JPanel buttonsPanel = new JPanel(new GridLayout(1, buttons.length, 15, 0));
        for (JButton b : buttons) {
            buttonsPanel.add(b);
        }

        return buttonsPanel;
    }

    private JScrollPane createScrollTable() {
        // Event to fillForm() every time user clicks on a table element
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() >= 0) {
                    // fillForm();
                }
            }
        });

        defineTable();
        table.getTableHeader().setReorderingAllowed(false);

        // Creates scrollable panel to contain table
        JScrollPane scrollable = new JScrollPane();
        scrollable.setPreferredSize(new Dimension(600, 325));
        scrollable.setViewportView(table);

        return scrollable;
    }

}