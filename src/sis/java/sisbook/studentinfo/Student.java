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
            if (grade.equals("A")){
                total += 4;
            } else if (grade.equals("B")) {
                total +=3;
            } else if (grade.equals("C")) {
                total += 2;
            } else if (grade.equals("D")) {
                total +=1;
            }
        }
        return total / grades.size();
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