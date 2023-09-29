package src.model;

public class Subject extends Entity {

    private String code;
    private String name;
    private int hours;
    private int totalVacancies;
    private int avaliableVacancies;
    private Professor professor;

    public Subject(String code, String name, int hours, int totalVacancies, Professor professor) {
        super(code);
        this.code = code;
        this.name = name;
        this.hours = hours;
        this.totalVacancies = totalVacancies;
        this.avaliableVacancies = totalVacancies;
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

    public int getAvaliableVacancies() {
        return avaliableVacancies;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setAvaliableVacancies(int avaliableVacancies) {
        this.avaliableVacancies = avaliableVacancies;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, name);
    }

}
