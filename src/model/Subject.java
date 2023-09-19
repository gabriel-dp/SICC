package src.model;

public class Subject extends Entity {

    private String code;
    private String name;
    private int hours;
    private int vacancies;
    private Professor professor;

    public Subject(String name, int hours, int vacancies, Professor professor) {
        super(name + professor);
        this.name = name;
        this.hours = hours;
        this.vacancies = vacancies;
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

    public int getVacancies() {
        return vacancies;
    }

    public Professor getProfessor() {
        return professor;
    }

    @Override
    public String toString() {
        return String.format("Subject{name=%s, hours=%d, vacancies=%s, professor=%s}",
                name,
                hours,
                vacancies,
                professor.getFirstName());
    }

}
