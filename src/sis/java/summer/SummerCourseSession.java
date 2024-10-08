package summer;

import java.util.*;

import studentinfo.*;

public class SummerCourseSession extends Session {
    public static Session create(Course course, Date startDate) {
        return new SummerCourseSession(course, startDate);
    }

    private SummerCourseSession(Course course, Date startDate) {
        super(course, startDate);
    }

    protected int getSessionLength() {
        return 8;
    }
}