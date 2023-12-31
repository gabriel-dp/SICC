package src.model;

public class Subject extends Entity {

    private String code;
    private String name;
    private int hours;
    private int totalVacancies;
    private Professor professor;

    public Subject(String code, String name, int hours, int totalVacancies, Professor professor) {
        super(code);
        this.code = code;
        this.name = name;
        this.hours = hours;
        this.totalVacancies = totalVacancies;
        this.professor = professor;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getTotalVacancies() {
        return totalVacancies;
    }

    public Professor getProfessor() {
        return professor;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, name);
    }

}
