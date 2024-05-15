package studentinfo;

import java.util.ArrayList;
class CourseSession {
    private final String department;
    private final String number;
    private final ArrayList<Student> students = new ArrayList<>();

    CourseSession(String department, String number){
        this.department = department;
        this.number = number;
    }

    String getDepartment(){
        return department;
    }
    String getNumber(){
        return number;
    }
    int getNumberOfStudents(){
        return  students.size();
    }

    void enroll(Student student){
        students.add(student);
    }

    Student get(int index){
        return students.get(index);
    }
}
