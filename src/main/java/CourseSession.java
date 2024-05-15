class CourseSession {
    private String department;
    private String number;
    private java.util.ArrayList<Student> students = new java.util.ArrayList<Student>();

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

    java.util.ArrayList<Student> getAllStudents(){
        return students;
    }
}
