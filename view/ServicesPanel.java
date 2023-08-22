package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AppController;

public class ServicesPanel extends JPanel {

    public ServicesPanel(AppFrame frame, AppController controller) {

        JLabel lblNewLabel = new JLabel(String.format("Welcome - %s", controller.getUserAuthenticated()));
        this.add(lblNewLabel);

    }

}
