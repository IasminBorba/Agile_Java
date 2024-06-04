package summer;

import java.util.*;
import studentinfo.*;

public class SummerCourseSession extends Session {
    public static SummerCourseSession create(String department, String number, Date startDate) {
        return new SummerCourseSession(department, number, startDate);
    }

    private SummerCourseSession(String department, String number, Date startDate) {
        super(department, number, startDate);
    }

    protected int getSessionLength() {
        return 8;
    }
}