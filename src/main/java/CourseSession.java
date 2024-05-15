import java.util.ArrayList;
class CourseSession {
    private String department;
    private String number;
    private ArrayList<Student> students = new ArrayList<Student>();

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

    ArrayList<Student> getAllStudents(){
        return students;
    }
}
