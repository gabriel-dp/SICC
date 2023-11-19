package src.utils.text;

public class FormatTrim extends Format {

    public FormatTrim(TextFormatter formatter) {
        super.formatter = formatter;
    }

    public String getText() {
        return formatter.getText().trim();
    }

    public void check() throws InvalidInputException {
        if (formatter.getText().trim().length() == 0)
            throw new InvalidInputException("Empty");
        formatter.check();
    }

}
