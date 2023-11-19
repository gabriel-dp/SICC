package src.utils.text;

public class FormatUppercase extends Format {

    public FormatUppercase(TextFormatter formatter) {
        super.formatter = formatter;
    }

    public String getText() {
        return formatter.getText().toUpperCase();
    }

    public void check() throws InvalidInputException {
        formatter.check();
    }

}
