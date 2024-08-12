package testing;

import junit.framework.*;

import java.util.*;

public class SuiteBuilderTest extends TestCase {
    public void testGatherTestClassNames() {
        SuiteBuilder builder = new SuiteBuilder();
        List<String> classes = builder.gatherTestClassNames();
        assertTrue(classes.contains("testing.SuiteBuilderTest"));
        assertFalse(classes.contains("testing.testclasses.NotATestClass"));
        assertFalse(classes.contains("testing.testclasses.AbstractTestClass"));
    }

    public void testAllTests() {
        SuiteBuilder builder = new SuiteBuilder();
        List<String> classes = builder.gatherTestClassNames();
        TestSuite suite = builder.suite();

        assertTrue(classes.contains("testing.SuiteBuilderTest"));
        assertTrue(classes.contains("db.DataFileTest"));
        assertTrue(classes.contains("report.ReportCardTest"));
        assertTrue(classes.contains("studentinfo.CourseTest"));
        assertTrue(classes.contains("summer.SummerCourseSessionTest"));
        assertTrue(classes.contains("ui.StudentUITest"));
        assertTrue(classes.contains("util.MathTest"));

        assertEquals(48, suite.testCount());
    }

    public void testCreateSuite() {
        SuiteBuilder builder = new SuiteBuilder() {
            public List<String> gatherTestClassNames() {
                List<String> classNames = new ArrayList<>();
                classNames.add("testing.SuiteBuilderTest");
                return classNames;
            }
        };
        TestSuite suite = builder.suite();
        assertEquals(1, suite.testCount());
        assertTrue(contains(suite, testing.SuiteBuilderTest.class));
    }

    public boolean contains(TestSuite suite, Class testClass) {
        List testClasses = Collections.list(suite.tests());
        for (Object object : testClasses) {
            if (object.getClass() == TestSuite.class)
                if (contains((TestSuite) object, testClass))
                    return true;
            if (object.getClass() == testClass)
                return true;
        }
        return false;
    }
}
