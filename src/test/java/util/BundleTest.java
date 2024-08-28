package util;

import junit.framework.TestCase;

import java.io.IOException;

public class BundleTest extends TestCase {
    private static final String KEY = "someKey";
    private static final String VALUE = "a value";
    private static final String TEST_APPEND = "Test";
    private static final String FILENAME = "src/sis/resources/util/" + Bundle.getName() + "Test.properties";
    private String existingBundleName;

    protected void setUp() {
        TestUtil.delete(FILENAME);
        existingBundleName = Bundle.getName();
        Bundle.setName(existingBundleName + TEST_APPEND);
    }

    protected void tearDown() {
        Bundle.setName(existingBundleName);

        TestUtil.delete(FILENAME);
    }

    public void testMessage() throws IOException {
        writeBundle();
        assertEquals(VALUE, Bundle.get(KEY));
    }

    private void writeBundle() throws IOException {
        String record = String.format("%s=%s", KEY, VALUE);
        LineWriter.write(FILENAME, new String[]{record});
    }
}
