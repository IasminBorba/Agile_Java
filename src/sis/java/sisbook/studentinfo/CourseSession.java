package sisbook.studentinfo;

import java.util.*;

public class CourseSession implements Comparable<CourseSession>{
    private final String department;
    private final String number;
    private final ArrayList<Student> students = new ArrayList<>();
    private final Date startDate;
    private static int count;
    private int numberOfCredits;

    private CourseSession(String department, String number, Date startDate) {
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

    Date getStartDate() {
        return startDate;
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
    Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        final int sessionLength = 16;final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        int numberOfDays =
                sessionLength * daysInWeek - daysFromFridayToMonday;
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