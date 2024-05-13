import junit.framework.TestCase;

public class StudentTest extends TestCase {
    public void testCreate() {
        Student student = new Student("Jane Doe");
        String studentName = student.getName();
        assertEquals("Jane Doe", studentName);
    }
}
