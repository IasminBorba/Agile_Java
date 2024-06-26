package studentinfo;

import junit.framework.TestCase;

import java.util.List;

public class CourseCatalogTest extends TestCase {
    private CourseCatalog catalog;
    private Session session1;
    private Session session2;
    private Course course1;
    private Course course2;

    protected void setup(){
        catalog = new CourseCatalog();
        course1 = new Course("a", "1");
        course2 = new Course("a", "1");

        session1 = CourseSession.create(course1, DateUtil.createDate(1,15,2005));
        session1.setNumberOfCredits(3);

        session2 = CourseSession.create(course2, DateUtil.createDate(1,17,2005));
        session2.setNumberOfCredits(5);

        catalog.add(session1);
        catalog.add(session2);
    }

    public void testStoreAndLoad() throws Exception{
        final String filename = "CourseCatalog.testAdd.txt";
        catalog.store(filename);
        catalog.clearAll();
        assertEquals(0, catalog.getSessions().size());
        catalog.load(filename);

        List<Session> sessions = catalog.getSessions();
        assertEquals(2, sessions.size());
        assertSession(session1, sessions.getFirst());
        assertSession(session2, sessions.get(1));
    }

    private void assertSession(Session expected, Session retrieved){
        assertNotSame(expected, retrieved);
        assertEquals(expected.getNumberOfCredits(), retrieved.getNumberOfCredits());
        assertEquals(expected.getStartDate(), retrieved.getStartDate());
        assertEquals(expected.getDepartment(), retrieved.getDepartment());
        assertEquals(expected.getNumber(), retrieved.getNumber());
    }
}
