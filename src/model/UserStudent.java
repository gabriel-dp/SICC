package src.model;

public class UserStudent extends User {

    private Course course;
    private Curriculum curriculum;

    public UserStudent(String username, String password, String firstName, String lastName, Course course) {
        super(username, password, firstName, lastName, RoleUser.STUDENT);
        this.course = course;
        curriculum = new Curriculum(username);
    }

    public Course getCourse() {
        return course;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    @Override
    public String toString() {
        return String.format("%s - %s ",
                super.getFullName(),
                course.getCode());
    }

}
