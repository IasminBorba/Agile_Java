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

    Date getStartDate() {
        return startDate;
    }
}