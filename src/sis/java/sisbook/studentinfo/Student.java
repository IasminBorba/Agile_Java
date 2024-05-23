package sisbook.studentinfo;


import java.util.ArrayList;

public class Student {
    private final ArrayList<String> grades = new ArrayList<>();
    private final String name;
    public double GPA;
    private int credits;
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "CO";
    public String state = "";

    public Student(String name) {
        this.name = name;
        credits = 0;
        this.GPA = getGpa();

    }
    public String getName() {
        return name;
    }

    public void addGrade(String grade){
        grades.add(grade);
    }

    double getGpa(){
        if (grades.isEmpty()){
            return 0.0;
        }
        double total = 0.0;
        for (String grade: grades){
            total += gradePointsFor(grade);
        }
        return total / grades.size();
    }

    int gradePointsFor(String grade){
        if (grade.equals("A")) return 4;
        if (grade.equals("B")) return 3;
        if (grade.equals("C")) return 2;
        if (grade.equals("D")) return 1;
        return 0;
    }

    boolean isFullTime(){
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    int getCredits() {
        return credits;
    }

    void addCredits(int credits) {
        this.credits += credits;
    }

    boolean isInState(){
        return state.equals(Student.IN_STATE);
    }

    void setState(String state){
        this.state = state.toUpperCase();
    }
}