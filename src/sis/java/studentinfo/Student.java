package studentinfo;


import java.util.*;

public  class Student implements Comparable{
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "CO";
    public String state = "";
    private String name;
    private int credits;
    public double GPA;
    private final ArrayList<Grade> grades = new ArrayList<>();

    @Override
    public int compareTo(Object o) {
        return 0;
    }

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
    private String firstName = "";
    private String middleName = "";
    private String lastName;

    private final List<Integer> charges = new ArrayList<Integer>();

    public Student(String fullName) {
        this.name = fullName;
        credits = 0;
        this.GPA = getGpa();

        List<String> nameParts = split(fullName);
        setName(nameParts);

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

    private void setName(List<String> nameParts){
        this.lastName = removeLast(nameParts);
        String name = removeLast(nameParts);

        if (nameParts.isEmpty()) {
            this.firstName = name;
        } else {
            this.middleName = name;
            this.firstName = removeLast(nameParts);
        }
    }

    private String removeLast(List<String> list){
        if(list.isEmpty()){
            return "";
        }
        return list.removeLast();
    }

    private List<String> split(String name) {
        List<String> results = new ArrayList<String>();
        StringBuffer word = new StringBuffer();
        for (int index = 0; index < name.length(); index++) {
            char ch = name.charAt(index);
            if (!Character.isWhitespace(ch))
                word.append(ch);
            else
            if (word.length() > 0) {
                results.add(word.toString());
                word = new StringBuffer();
            }
        }
        if (word.length() > 0)
            results.add(word.toString());
        return results;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getMiddleName(){
        return middleName;
    }

    public String getLastName(){
        return lastName;
    }

    public void addCharge(int charge) {
        charges.add(charge);
    }

    public int totalCharges(){
        int total = 0;
        for (Integer charge: charges) {
            total += charge;
        }
        return total;
    }
}