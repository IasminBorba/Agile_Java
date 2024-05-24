package sisbook.studentinfo;


import java.util.ArrayList;

public class Student{
    enum Grade {A, B, C, D, F}
    private boolean isHonors = false;
    private final ArrayList<Grade> grades = new ArrayList<>();
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

    void setHonors() {
        isHonors = true;
    }
    public String getName() {
        return name;
    }

    public void addGrade(Grade grade){
        grades.add(grade);
    }

    double getGpa(){
        if (grades.isEmpty()){
            return 0.0;
        }
        double total = 0.0;
        for (Grade grade: grades){
            total += gradePointsFor(grade);
        }
        return total / grades.size();
    }

    int gradePointsFor(Grade grade){
        int points = basicGradePointsFor(grade);
        if (isHonors){
            if(points > 0){
                points += 1;
            }
        }
        return points;
    }

    private int basicGradePointsFor(Grade grade) {
        if (grade.equals(Grade.A)) return 4;
        if (grade.equals(Grade.B)) return 3;
        if (grade.equals(Grade.C)) return 2;
        if (grade.equals(Grade.D)) return 1;
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