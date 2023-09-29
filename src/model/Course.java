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
        this.code = code;
        this.name = name;
        this.type = type;
        this.shift = shift;
        this.semesters = semesters;
        this.subjects = subjects;
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
        return String.format("%s", code);
    }

}
