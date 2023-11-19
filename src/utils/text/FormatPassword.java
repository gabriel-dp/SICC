package src.utils.text;

public class FormatPassword extends Format {
    public FormatPassword(TextFormatter formatter) {
        super.formatter = formatter;
    }

    public String getText() {
        return formatter.getText();
    }

    public void check() throws InvalidInputException {
        if (!formatter.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"))
            throw new InvalidInputException("Invalid Password");
        formatter.check();
    }
}
