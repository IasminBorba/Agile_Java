package studentinfo;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
public class CourseSessionTest extends TestCase{
    public void testCreate(){
        CourseSession session = new CourseSession("ENGL", "101");
        Assert.assertEquals("101", session.getNumber());
        Assert.assertEquals("ENGL", session.getDepartment());
        Assert.assertEquals(0, session.getNumberOfStudents());
    }
    public void testEnrollStudents(){
        CourseSession session = new CourseSession("ENGL", "101");

        Student student1 = new Student("Cain DiVoe");
        session.enroll(student1);
        Assert.assertEquals(1, session.getNumberOfStudents());
        ArrayList<Student> allStudents = session.getAllStudents();
        Assert.assertEquals(1, allStudents.size());
        Assert.assertEquals(student1, allStudents.getFirst());

        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        Assert.assertEquals(2, session.getNumberOfStudents());
        Assert.assertEquals(2, allStudents.size());
        Assert.assertEquals(student1, allStudents.get(0));
        Assert.assertEquals(student2, allStudents.get(1));
    }
}
