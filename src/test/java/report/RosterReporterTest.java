package report;

import junit.framework.TestCase;
import studentinfo.Course;
import studentinfo.CourseSession;
import studentinfo.DateUtil;
import studentinfo.Student;

import static report.ReportConstant.NEWLINE;

public class RosterReporterTest extends TestCase {
    public void testRosterReport() {
        CourseSession session = CourseSession.create(new Course("ENGL", "101"), DateUtil.createDate(2003, 1, 6));
        session.enroll(new Student("A"));
        session.enroll(new Student("B"));
        String rosterReport = new RosterReporter(session).getReport();
        assertEquals(
        RosterReporter.ROSTER_REPORT_HEADER +
                "A" + NEWLINE +
                "B" + NEWLINE +
                RosterReporter.ROSTER_REPORT_FOOTER + "2" + NEWLINE,
                rosterReport
        );
    }
}