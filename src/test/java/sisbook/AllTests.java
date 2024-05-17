package sisbook;
import junit.framework.TestSuite;
import sisbook.studentinfo.CourseSessionTest;
import sisbook.studentinfo.DateUtilTest;
import sisbook.report.RosterReporterTest;
import sisbook.studentinfo.StudentTest;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        suite.addTestSuite(RosterReporterTest.class);
        suite.addTestSuite(DateUtilTest.class);
        return suite;
    }
}
