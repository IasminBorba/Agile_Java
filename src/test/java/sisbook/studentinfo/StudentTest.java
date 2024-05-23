package sisbook.studentinfo;

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

    public void testCalculateGpa(){
        Student student = new Student("a");
        assertEquals(0.0, student.getGpa(), GRADE_TOLERANCE);

        student.addGrade("A");
        assertEquals(4.0, student.getGpa(), GRADE_TOLERANCE);

        student.addGrade("B");
        assertEquals(3.5, student.getGpa(), GRADE_TOLERANCE);

        student.addGrade("C");
        assertEquals(3.0, student.getGpa(), GRADE_TOLERANCE);

        student.addGrade("D");
        assertEquals(2.5, student.getGpa(), GRADE_TOLERANCE);

        student.addGrade("F");
        assertEquals(2.0, student.getGpa(), GRADE_TOLERANCE);
    }
}
