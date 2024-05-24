package studentinfo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StudentTest extends TestCase {
    private static final double GRADE_TOLERANCE = 0.85;
    public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        Assert.assertEquals(firstStudentName, firstStudent.getName());

        final String secondStudentName = "Joe Blow";
        Student secondStudent = new Student(secondStudentName);
        Assert.assertEquals(secondStudentName, secondStudent.getName());
    }

    public void testStudentStatus() {
        Student student = new Student("a");

        assertEquals(0, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(3);
        assertEquals(3, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(4);
        assertEquals(7, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(5);
        assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
        assertTrue(student.isFullTime());
    }

    public void testInState(){
        Student student = new Student("a");

        assertFalse(student.isInState());

        student.setState(Student.IN_STATE);
        assertTrue(student.isInState());

        student.setState("ND");
        assertFalse(student.isInState());

        student.setState("co");
        assertTrue(student.isInState());

        student.setState("Co");
        assertTrue(student.isInState());
    }

    public void testCalculateGpa() {
        Student student = new Student("a");
        assertGpa(0.0, student);
        student.addGrade(Student.Grade.A);
        assertGpa(4.0, student);
        student.addGrade(Student.Grade.B);
        assertGpa(3.5, student);
        student.addGrade(Student.Grade.C);
        assertGpa(3.0, student);
        student.addGrade(Student.Grade.D);
        assertGpa(2.5, student);
        student.addGrade(Student.Grade.F);
        assertGpa(2.0, student) ;
    }

    private void assertGpa(double expectedGpa, Student student) {
        assertEquals(expectedGpa, student.getGpa(), GRADE_TOLERANCE);
    }

    public void testCalculateHonorsStudentGpa() {
        assertGpa(0.0, createHonorsStudent());
        assertGpa(5.0, createHonorsStudent(Student.Grade.A));
        assertGpa(4.0, createHonorsStudent(Student.Grade.B));
        assertGpa(3.0, createHonorsStudent(Student.Grade.C));
        assertGpa(2.0, createHonorsStudent(Student.Grade.D));
        assertGpa(0.0, createHonorsStudent(Student.Grade.F));
    }
    private Student createHonorsStudent(Student.Grade grade) {
        Student student = createHonorsStudent();
        student.addGrade(grade);
        return student;
    }
    private Student createHonorsStudent() {
        Student student = new Student("a");
        student.setGradingStrategy(new HonorsGradingStrategy());
        return student;
    }
}