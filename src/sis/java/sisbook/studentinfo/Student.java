package sisbook.studentinfo;

public class Student {
    private final String name;
    private int credits;
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public Student(String name) {
        this.name = name;
        credits = 0;
    }
    public String getName() {
        return name;
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
}