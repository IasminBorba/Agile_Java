package studentinfo;

import java.util.*;

class CourseSession {
    private final String department;
    private final String number;
    private final ArrayList<Student> students = new ArrayList<>();
    private final Date startDate;

    ArrayList<Student> getAllStudents() {
        return students;
    }

    CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
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

    void enroll(Student student) {
        students.add(student);
    }

    Student get(int index) {
        return students.get(index);
    }

    Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        final int sessionLength = 16;
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        int numberOfDays = sessionLength * daysInWeek - daysFromFridayToMonday;

        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    Date getStartDate() {
        return startDate;
    }
}