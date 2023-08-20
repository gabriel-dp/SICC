package model;

public class Professor extends Entity {

    private String firstName;
    private String lastName;
    private String email;

    public Professor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Professor{firstName=%s, lastName=%s, email=%s}",
                firstName,
                lastName,
                email);
    }

}
