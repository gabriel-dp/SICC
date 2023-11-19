package src.utils.text;

public class FormatOnlyNumbers extends Format {

    public FormatOnlyNumbers(TextFormatter formatter) {
        super.formatter = formatter;
    }

    public String getText() {
        return formatter.getText();
    }

    public void check() throws InvalidInputException {
        if (!formatter.getText().matches("\\d+")) {
            System.out.println("Nao tem só numeros|" + formatter.getText());
            throw new InvalidInputException("Only Numbers");
        }
        formatter.check();
    }

}
