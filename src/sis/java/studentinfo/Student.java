package studentinfo;


import java.util.ArrayList;

public class Student {
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "CO";
    public String state = "";
    private final String name;
    private int credits;
    public double GPA;
    private final ArrayList<Grade> grades = new ArrayList<>();
    public enum Grade {
        A(4), B(3), C(2), D(1), F(0);

        private final int points;

        Grade(int points){
            this.points = points;
        }

        int getPoints(){
            return points;
        }
    }
    private GradingStrategy gradingStrategy = new BasicGradingStrategy();

    public Student(String name) {
        this.name = name;
        credits = 0;
        this.GPA = getGpa();

    }

    public String getName() {
        return name;
    }

    boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    int getCredits() {
        return credits;
    }

    void addCredits(int credits) {
        this.credits += credits;
    }

    boolean isInState() {
        return state.equals(Student.IN_STATE);
    }

    void setState(String state) {
        this.state = state.toUpperCase();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    double getGpa() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Grade grade : grades) {
            total += gradingStrategy.getGradePointsFor(grade);
        }
        return total / grades.size();
    }


    void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }
}