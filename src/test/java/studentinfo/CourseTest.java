package studentinfo;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CourseTest extends TestCase {
    String date = "04/19/2024";

    public void testCreate() {
        Course course = new Course("CMSC", "120", date);
        assertEquals("CMSC", course.getDepartment());
        assertEquals("120", course.getNumber());
        assertEquals("04/19/2024", course.getEffectiveDate());
    }

    public void testEquality() {
        Course courseA = new Course("NURS", "201", "");
        Course courseAPrime = new Course("NURS", "201", "");
        assertEquals(courseA, courseAPrime);

        Course courseB = new Course("ARTH", "330", "");
        assertFalse(courseA.equals(courseB));

        Course courseC = new Course("NURS", "201", date);
        assertFalse(courseA.equals(courseC));

        assertEquals(courseA, courseA);

        Course courseAPrime2 = new Course("NURS", "201", "");
        assertEquals(courseAPrime, courseAPrime2);
        assertEquals(courseA, courseAPrime2);

        assertEquals(courseAPrime, courseA);
        assertEquals(courseA, courseAPrime);

        assertTrue(courseA.equals(courseAPrime));
        assertFalse(courseA == courseAPrime);
        assertNotSame(courseA, courseAPrime);

        assertFalse(courseA.equals(null));
        assertFalse(courseA.equals("CMSC-120"));
    }

    public void testHashCode() {
        Course courseA = new Course("NURS", "201", "");
        Course courseAPrime = new Course("NURS", "201", "");
        Course courseB = new Course("NURS", "201", date);

        assertEquals(courseA.hashCode(), courseAPrime.hashCode());
        assertEquals(courseA.hashCode(), courseA.hashCode());
        assertNotEquals(courseA.hashCode(), courseB.hashCode());
    }

    public void testHashCodePerformance() {
        final int count = 20000;
        long start = System.currentTimeMillis();
        Map<Course, String> map = new HashMap<>();

        for (int i = 0; i < count; i++) {
            Course course = new Course("C" + i, "" + i, "");
            map.put(course, "");
        }

        long stop = System.currentTimeMillis();
        long elapsed = stop - start;
        final long arbitraryThreshold = 200;
        assertTrue("elapsed time = " + elapsed, elapsed < arbitraryThreshold);
    }

    public void testToString() {
        Course course = new Course("ENGL", "301", "");
        String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yy"));
        assertEquals("ENGL-301: " + localDate, course.toString());

        Course course2 = new Course("NURS", "305", date);
        assertEquals("NURS-305: 04/19/24", course2.toString());
    }

    public void testClone() {
        final String department = "CHEM";
        final String number = "400";
        final LocalDate date = (LocalDateTime.now().toLocalDate());
        Course course = new Course(department, number, date.toString());

        Course copy = course.clone();
        assertTrue(course.equals(copy));
    }
}
