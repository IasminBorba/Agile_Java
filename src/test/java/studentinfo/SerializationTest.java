package studentinfo;

import junit.framework.*;

public class SerializationTest extends TestCase {
    public void testLoadToNewVersion() throws Exception {
        CourseCatalog catalog = new CourseCatalog();
        catalog.load("CourseCatalogTest.testAdd.txt");
        assertEquals(2, catalog.getSessions().size());
    }
}
