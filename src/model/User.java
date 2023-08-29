package src.model;

public abstract class User extends Entity {

    private RoleUser role;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(RoleUser role, String username, String password, String firstName, String lastName) {
        super(username);
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

    public RoleUser getRole() {
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
