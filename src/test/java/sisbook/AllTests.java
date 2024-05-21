package sisbook;

import junit.framework.TestSuite;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(sisbook.report.AllTests.suite());
        suite.addTest(sisbook.studentinfo.AllTests.suite());
        return suite;
    }
}
