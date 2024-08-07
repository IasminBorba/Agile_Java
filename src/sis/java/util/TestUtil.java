package util;

import junit.framework.Assert;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class TestUtil {
    public static void assertGone(String... filenames){
        for(String filename: filenames)
            Assert.assertFalse(new File(filename).exists());
    }

    public static void delete(String filename){
        File file = new File(filename);
        if(file.exists())
            Assert.assertTrue(file.delete());
    }

    public static void assertDateEquals(int year, int month, int day, Date actualDate) {
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.set(year, month - 1, day, 0, 0, 0);
        expectedCalendar.set(Calendar.MILLISECOND, 0);
        Date expectedDate = expectedCalendar.getTime();

        assertEquals("Dates are not equal", expectedDate, actualDate);
    }
}
