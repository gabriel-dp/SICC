package src.model;

import java.util.ArrayList;
import java.util.Date;

public class Curriculum extends Entity {

    private Date lastEdit;
    private ArrayList<Subject> subjects;

    public Curriculum(String id) {
        super(id);
        lastEdit = new Date();
        subjects = new ArrayList<>();
    }

    public int getTotalHours() {
        int total = 0;
        for (Subject subject : subjects) {
            total += subject.getHours();
        }
        return total;
    }

    public Date getLastEdit() {
        return lastEdit;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Curriculum{lastEdit=%s",
                lastEdit));
        sb.append(", subjects=[");

        for (Subject subject : subjects) {
            sb.append(subject + "\n");
        }
        sb.append("]");

        return sb.toString();
    }

}
