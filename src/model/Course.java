package src.model;

import java.util.ArrayList;

public class Course extends Entity {

    private String code;
    private String name;
    private String type;
    private String shift;
    private int semesters;
    private ArrayList<Subject> subjects;

    public Course(String code, String name, String type, String shift, int semesters, ArrayList<Subject> subjects) {
        super(code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return this.type;
    }

    public String getShift() {
        return this.shift;
    }

    public int getSemesters() {
        return this.semesters;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Course{name=%s, type=%s, shift=%s, semesters=%d}",
                name,
                type,
                shift,
                semesters));
        sb.append(", subjects=[");

        for (Subject subject : subjects) {
            sb.append(subject + "\n");
        }
        sb.append("]");

        return sb.toString();
    }

}
