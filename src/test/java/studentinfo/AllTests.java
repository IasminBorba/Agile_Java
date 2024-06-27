package studentinfo;
import junit.framework.TestSuite;
import summer.SummerCourseSessionTest;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(AccountTest.class);
        suite.addTestSuite(BasicGradingStrategyTest.class);
        suite.addTestSuite(CourseCatalogTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        suite.addTestSuite(CourseTest.class);
        suite.addTestSuite(DateUtilTest.class);
        suite.addTestSuite(HonorsGradingStrategyTest.class);
        suite.addTestSuite(PerformanceTest.class);
        suite.addTestSuite(ScorerTest.class);
        suite.addTestSuite(StudentDirectoryTest.class);
        suite.addTestSuite(SummerCourseSessionTest.class);
        suite.addTestSuite(StudentTest.class);
        return suite;
    }
}
