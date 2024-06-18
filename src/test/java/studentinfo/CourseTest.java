package studentinfo;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CourseTest extends TestCase {
    public void testCreate(){
        Course course = new Course("CMSC", "120");
        assertEquals("CMSC", course.getDepartment());
        assertEquals("120", course.getNumber());
    }

    public void testEquality() {
        Course courseA = new Course("NURS", "201");
        Course courseAPrime = new Course("NURS", "201");
        assertEquals(courseA, courseAPrime);
        Course courseB = new Course("ARTH", "330");
        assertFalse(courseA.equals(courseB));

        assertEquals(courseA,courseA);

        Course courseAPrime2 = new Course("NURS", "201");
        assertEquals(courseAPrime, courseAPrime2);
        assertEquals(courseA, courseAPrime2);

        assertEquals(courseAPrime, courseA);

        assertEquals(courseA, courseAPrime);
        assertTrue(courseA.equals(courseAPrime));
        assertFalse(courseA == courseAPrime);
        assertNotSame(courseA, courseAPrime);

        assertFalse(courseA.equals(null));
        assertFalse(courseA.equals("CMSC-120"));


        Map<Course, String> map = new HashMap<>();
        map.put(courseA, "");
        assertTrue(map.containsKey(courseA));
    }
}
