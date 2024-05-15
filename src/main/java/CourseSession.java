class CourseSession {
    private String department;
    private String number;
    private int numberOfStudents;
    private java.util.ArrayList<Student> students = new java.util.ArrayList<Student>();

    CourseSession(String department, String number){
        this.department = department;
        this.number = number;
        numberOfStudents = 0;
    }

    String getDepartment(){
        return department;
    }
    String getNumber(){
        return number;
    }
    int getNumberOfStudents(){
        return  numberOfStudents;
    }

    void enroll(Student student){
        numberOfStudents = numberOfStudents+1;
        students.add(student);
    }

    java.util.ArrayList<Student> getAllStudents(){
        return students;
    }
}
