package sisbook.studentinfo;

import java.util.*;
import junit.framework.Assert;
import junit.framework.TestCase;

public class CourseSessionTest extends TestCase{
    public CourseSession session;
    public Date startDate;

    public void setUp() {
        startDate = DateUtil.createDate(2003,1, 6);
        session = new CourseSession("ENGL", "101", startDate);
    }
    public void testCreate(){
        assertEquals("101", session.getNumber());
        assertEquals("ENGL", session.getDepartment());
        assertEquals(0, session.getNumberOfStudents());
        assertEquals(startDate, session.getStartDate());
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