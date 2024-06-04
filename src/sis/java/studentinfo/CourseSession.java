package studentinfo;

import java.util.*;

public class CourseSession extends Session {
    private static int count;

    public static CourseSession create(String department, String number, Date startDate) {
        return new CourseSession(department, number, startDate);
    }
    protected CourseSession(String department, String number, Date startDate) {
        super(department, number, startDate);
        CourseSession.incrementCount();
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