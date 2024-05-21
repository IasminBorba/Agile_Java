package sisbook.studentinfo;

import java.util.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import static sisbook.studentinfo.DateUtil.createDate;

public class CourseSessionTest extends TestCase{
    public CourseSession session;
    public Date startDate;

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
        return new CourseSession("ENGL", "101", startDate);
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
        Assert.assertEquals(1, session.getNumberOfStudents());
        Assert.assertEquals(student1, session.get(0));

        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        Assert.assertEquals(2, session.getNumberOfStudents());
        Assert.assertEquals(student1, session.get(0));
        Assert.assertEquals(student2, session.get(1));
    }
}
