package sisbook.report;

import junit.framework.TestCase;
import sisbook.studentinfo.Student;


public class ReportCardTest extends TestCase {
    public void testReport() {
        ReportCard card = new ReportCard();
        Student student = new Student("a");
        student.addGrade(10);
        student.addGrade(8);
        card.add(student);

        assertEquals(9, card.textGPA());
    }
}
