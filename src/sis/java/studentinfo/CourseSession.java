package studentinfo;

import java.util.*;

public class CourseSession extends Session {
    private static int count;

    public static Session create(Course course, Date startDate) {
        incrementCount();
        return new CourseSession(course, startDate);
    }

    protected CourseSession(Course course, Date startDate) {
        super(course, startDate);
    }

    static private void incrementCount() {
        ++count;
    }

    static void resetCount() {
        count = 0;
    }

    static int getCount() {
        return count;
    }

    protected int getSessionLength() {
        return 16;
    }
}