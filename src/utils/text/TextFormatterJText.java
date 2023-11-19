package src.utils.text;

import javax.swing.text.JTextComponent;

public class TextFormatterJText implements TextFormatter {

    private JTextComponent tf;

    public TextFormatterJText(JTextComponent tf) {
        this.tf = tf;
    }

    public String getText() {
        return tf.getText();
    }

    public void check() throws InvalidInputException {
    }

}
