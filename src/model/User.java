package src.model;

public abstract class User extends Entity {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private RoleUser role;

    public User(String username, String password, String firstName, String lastName, RoleUser role) {
        super(username);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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

    public RoleUser getRole() {
        return role;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", username, getFullName());
    }

}
