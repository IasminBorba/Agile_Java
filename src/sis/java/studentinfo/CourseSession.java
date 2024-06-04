package studentinfo;

import java.util.*;

public class CourseSession implements Comparable<CourseSession>{
    private final String department;
    private final String number;
    private final ArrayList<Student> students = new ArrayList<>();
    public final Date startDate;
    private static int count;
    private int numberOfCredits;

    protected CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }

    public static CourseSession create(String department, String number, Date startDate) {
        incrementCount();
        return new CourseSession(department, number, startDate);
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    int getNumberOfStudents() {
        return students.size();
    }

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student) ;
    }

    Student get(int index) {
        return students.get(index);
    }

    protected Date getStartDate() {
        return startDate;
    }

    protected int getSessionLength() {
        return 16;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    private static void incrementCount(){
        count++;
    }

    static void resetCount(){
        count = 0;
    }
    static int getCount(){
        return count;
    }

    void setNumberOfCredits(int numberOfCredits){
        this.numberOfCredits = numberOfCredits;
    }

    /**
     * @return Date a última data da sessão do curso
     */
    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    public int compareTo(CourseSession that) {
        int compare = this.getDepartment().compareTo(that.getDepartment());
        if (compare != 0) {
            return compare;
        }
        return this.getNumber().compareTo(that.getNumber());
    }
}