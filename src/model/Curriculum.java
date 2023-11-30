package src.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Curriculum extends Entity {

    private Date lastEdit;
    private ArrayList<Subject> subjects;

    public Curriculum(String username) {
        super(username);
        lastEdit = null;
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

    public String getFormattedLastEdit() {
        return lastEdit == null ? "-" : new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(lastEdit);
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
        this.lastEdit = new Date();
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
