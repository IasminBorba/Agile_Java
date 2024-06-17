package studentinfo;


import java.util.*;
import java.util.logging.*;

public  class Student implements Comparable{
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "CO";
    public String state = "";
    private final String name;
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
    static final int MAX_NAME_PARTS = 3;
    static final String TOO_MANY_NAME_PARTS_MSG = "Student name '%s' contains more than %d parts";
    private final List<Integer> charges = new ArrayList<>();
    final static Logger logger = Logger.getLogger(Student.class.getName());

    public Student(String fullName) {
        this.name = fullName;
        credits = 0;

        List<String> nameParts = split(fullName);

        if(nameParts.size() > MAX_NAME_PARTS){
            String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG, fullName, MAX_NAME_PARTS);
            Student.logger.info(message);
            throw new StudentNameFormatException(message);
        }
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
        Student.logger.fine("begin getGpa " + System.currentTimeMillis());
        if (grades.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Grade grade : grades) {
            total += gradingStrategy.getGradePointsFor(grade);
        }
        Student.logger.fine("end getGpa " + System.currentTimeMillis());
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

    private List<String> split(String fullName) {
        List<String> results = new ArrayList<>();
        for (String name: fullName.split(" ")){
            results.add(name);
        }
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