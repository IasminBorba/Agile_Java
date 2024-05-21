package sisbook.studentinfo;

import java.util.*;

public class CourseSession {
    private final String department;
    private final String number;
    private final ArrayList<Student> students = new ArrayList<>();
    private final Date startDate;
    private static int count;

    public CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
        CourseSession.incrementCount();
    }

    String getDepartment() {
        return department;
    }

    String getNumber() {
        return number;
    }

    int getNumberOfStudents() {
        return students.size();
    }

    public void enroll(Student student) {
        students.add(student);
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
}