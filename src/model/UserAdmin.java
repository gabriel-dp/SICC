package src.model;

public class UserAdmin extends User {

    public UserAdmin(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, RoleUser.ADMIN);
    }

    @Override
    public String toString() {
        return String.format("UserAdmin{%s}",
                super.toString());
    }

}
