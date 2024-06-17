package report;

import junit.framework.*;
import java.util.*;
import studentinfo.*;

import static report.RosterReporter.NEWLINE;


public class CourseReportTest extends TestCase {
    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(create("ENGL", "101", date));
        report.add(create("CZEC", "200", date));
        report.add(create("ITAL", "410", date));
        report.add(create("CZEC", "220", date));
        report.add(create("ITAL", "330", date));
        assertEquals(
        "CZEC 200" + NEWLINE +
                "CZEC 220" + NEWLINE +
                "ENGL 101" + NEWLINE +
                "ITAL 330" + NEWLINE +
                "ITAL 410" + NEWLINE,
                report.text()
        );
    }

    private CourseSession create(String name, String number, Date date) {
        return CourseSession.create(new Course(name, number), date);
    }
}
