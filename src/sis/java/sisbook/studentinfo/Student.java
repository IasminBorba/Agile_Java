package sisbook.studentinfo;


public class Student {
    private final String name;
    public int GPA;
    private int credits;
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "CO";
    public String state = "";

    public Student(String name) {
        this.name = name;
        credits = 0;
        this.GPA = getGPA();

    }
    public String getName() {
        return name;
    }

    public void addGrade(int grade){
        GPA += grade;
    }

    public int getGPA(){
        return GPA;
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