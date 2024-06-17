package report;

import junit.framework.*;
import java.util.*;

import studentinfo.Course;
import studentinfo.CourseSession;

import static report.RosterReporter.NEWLINE;


public class CourseReportTest extends TestCase {
    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create(new Course("ENGL", "101"), date));
        report.add(CourseSession.create(new Course("CZEC", "200"), date));
        report.add(CourseSession.create(new Course("ITAL", "410"), date));
        report.add(CourseSession.create(new Course("CZEC", "220"), date));
        report.add(CourseSession.create(new Course("ITAL", "330"), date));
        assertEquals(
        "CZEC 200" + NEWLINE +
                "CZEC 220" + NEWLINE +
                "ENGL 101" + NEWLINE +
                "ITAL 330" + NEWLINE +
                "ITAL 410" + NEWLINE,
                report.text()
        );
    }
}
