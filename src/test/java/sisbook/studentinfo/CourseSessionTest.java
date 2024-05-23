package sisbook.studentinfo;

import java.util.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import static sisbook.studentinfo.DateUtil.createDate;

public class CourseSessionTest extends TestCase{
    public CourseSession session;
    public Date startDate;
    private static final int CREDITS = 3;
    public void setUp() {
        startDate = createDate(2003,1, 6);
        session = createCourseSession();
    }
    public void testCount() {
        CourseSession.resetCount();
        createCourseSession();
        assertEquals(1, CourseSession.getCount());
        createCourseSession();
        assertEquals(2, CourseSession.getCount());
        startDate = DateUtil.createDate(2003,1, 6);
        session = createCourseSession();
    }

    private CourseSession createCourseSession() {
        CourseSession session = CourseSession.create("ENGL", "101", startDate);
        session.setNumberOfCredits(CourseSessionTest.CREDITS);
        return session;
    }

    public void testCourseDates() {
        Date sixteenWeeksOut = createDate(2003, 4, 25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

    public void testCreate(){
        assertEquals("101", session.getNumber());
        assertEquals("ENGL", session.getDepartment());
        assertEquals(0, session.getNumberOfStudents());
        assertEquals(startDate, session.getStartDate());

        System.out.println(session.getEndDate());
    }

    public void testEnrollStudents(){
        Student student1 = new Student("Cain DiVoe");
        session.enroll(student1);
        assertEquals(CREDITS, student1.getCredits());
        Assert.assertEquals(1, session.getNumberOfStudents());
        Assert.assertEquals(student1, session.get(0));

        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        assertEquals(CREDITS, student2.getCredits());
        Assert.assertEquals(2, session.getNumberOfStudents());
        Assert.assertEquals(student1, session.get(0));
        Assert.assertEquals(student2, session.get(1));
    }

    public void testComparable() {
        final Date date = new Date();
        CourseSession sessionA = CourseSession.create("CMSC", "101", date);
        CourseSession sessionB = CourseSession.create("ENGL", "101", date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);

        CourseSession sessionC = CourseSession.create("CMSC", "101", date);
        assertEquals(0, sessionA.compareTo(sessionC));
        CourseSession sessionD = CourseSession.create("CMSC", "210", date);
        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }
}
