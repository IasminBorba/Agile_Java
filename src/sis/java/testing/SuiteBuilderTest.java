package testing;

import junit.framework.TestCase;
import java.util.*;

public class SuiteBuilderTest extends TestCase {
    public void testGatherTestClassNames(){
        SuiteBuilder builder = new SuiteBuilder();
        List<String> classes = builder.gatherTestClassNames();
        assertTrue(classes.contains("testing.SuiteBuilderTest"));
    }
}
