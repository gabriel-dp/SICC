package model;

public abstract class User extends Entity {

    private String role;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(String role, String username, String password, String firstName, String lastName) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("User={username=%s, password=%s, firstname=%s, lastname=%s, role=%s}",
                username,
                password,
                firstName,
                lastName,
                role);
    }

}
