package summer;

import java.util.*;

import studentinfo.*;
import ui.DateUtil;

public class SummerCourseSessionTest extends SessionTest {
    public void testEndDate() {
        Date startDate = DateUtil.createDate(2003, 6, 9);
        Session session = createSession(new Course("ENGL", "200", "10/08/2024"), startDate);
        Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeeksOut, session.getEndDate());
    }

    protected Session createSession(Course course, Date date) {
        return SummerCourseSession.create(course, date);
    }
}
