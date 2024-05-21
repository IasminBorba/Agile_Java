package sisbook.studentinfo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StudentTest extends TestCase {
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
        assertEquals(12, student.getCredits());
        assertTrue(student.isFullTime());
    }
}
