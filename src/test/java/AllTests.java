import junit.framework.TestSuite;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(report.AllTests.suite());
        suite.addTest(studentinfo.AllTests.suite());
        suite.addTestSuite(db.DataFileTest.class);
        suite.addTestSuite(db.KeyFileTest.class);
        suite.addTestSuite(ui.StudentUITest.class);
        suite.addTestSuite(util.InfinityTest.class);
        suite.addTestSuite(util.IOUtilTest.class);
        suite.addTestSuite(util.MathTest.class);
        suite.addTestSuite(util.ParityCheckerTest.class);
        suite.addTestSuite(util.PasswordGeneratorTest.class);
        return suite;
    }
}
