package src.model;

public class UserAdmin extends User {

    public UserAdmin(String username, String password, String firstName, String lastName) {
        super(RoleUser.ADMIN, username, password, firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("UserAdmin{%s}",
                super.toString());
    }

}