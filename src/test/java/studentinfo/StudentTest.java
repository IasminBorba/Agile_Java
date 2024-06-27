package studentinfo;

import junit.framework.TestCase;
import java.util.logging.*;

public class StudentTest extends TestCase {
    private static final double GRADE_TOLERANCE = 0.85;

    public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudentName, firstStudent.getName());
        assertEquals("Jane", firstStudent.getFirstName());
        assertEquals("Doe", firstStudent.getLastName());
        assertEquals("", firstStudent.getMiddleName());

        final String secondStudentName = "Joe Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());
        assertEquals("Joe", secondStudent.getFirstName());
        assertEquals("Blow", secondStudent.getLastName());
        assertEquals("", secondStudent.getMiddleName());

        final String thirdStudentName = "Raymond Douglas Davies";
        Student thirdStudent = new Student(thirdStudentName);
        assertEquals("Raymond", thirdStudent.getFirstName());
        assertEquals("Davies", thirdStudent.getLastName());
        assertEquals("Douglas", thirdStudent.getMiddleName());
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

    public void testCharges(){
        Student student = new Student("a");
        student.addCharge(500);
        student.addCharge(200);
        student.addCharge(399);

        assertEquals(1099, student.totalCharges());
    }

    public void testBadlyFormattedName(){
        Handler handler = new TestHandler();
        Student.logger.addHandler(handler);
        final String studentName = "a b c d";
        try {
            new Student("a b c d");
            fail("expected exception from 4-part name");
        } catch (StudentNameFormatException expectedException){
            String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG, studentName, Student.MAX_NAME_PARTS);
            assertEquals(message, expectedException.getMessage());
            assertEquals(message, ((TestHandler)handler).getMessage());
        }
    }

    public void testLoggingHierarchy() {
        Logger logger = Logger.getLogger("sis.studentinfo.Student");
        assertTrue(logger == Logger.getLogger("sis.studentinfo.Student"));
        Logger parent = Logger.getLogger("sis.studentinfo");
        assertEquals(parent, logger.getParent());
        assertEquals(Logger.getLogger("sis"), parent.getParent());
    }

    public void testFlags(){
        Student student = new Student("a");
        student.set(
                Student.Flag.ON_CAMPUS,
                Student.Flag.TAX_EXEMPT,
                Student.Flag.MINOR
        );
        assertTrue(student.isOn(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
        assertTrue(student.isOn(Student.Flag.MINOR));

        assertFalse(student.isOff(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOff(Student.Flag.TROUBLEMAKER));

        student.unset(Student.Flag.ON_CAMPUS);
        assertTrue(student.isOff(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
        assertTrue(student.isOn(Student.Flag.MINOR));
    }
}
