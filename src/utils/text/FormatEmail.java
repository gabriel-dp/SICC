package src.utils.text;

public class FormatEmail extends Format {

    public FormatEmail(TextFormatter formatter) {
        super.formatter = formatter;
    }

    public String getText() {
        return formatter.getText();
    }

    public void check() throws InvalidInputException {
        if (!formatter.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new InvalidInputException("Invalid Email");
        formatter.check();
    }

}
