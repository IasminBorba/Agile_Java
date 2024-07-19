package clock;

import junit.framework.TestCase;
import java.util.*;
import static clock.AlarmClock.convertHourText;

public class AlarmClockTest extends TestCase {
    private AlarmClock clock;
    public static List<List<Object>> alarms;

    public void testAlarm() throws InterruptedException {
        alarms = new ArrayList<>();
        String alarm = "11:54";
        String textAlarm = "Wake up!";
        alarms.add(convertHourText(alarm, textAlarm));

        clock = new AlarmClock(alarms);

        String textAlarms =
                "Fri Jul 19 11:53:00 BRT 2024\n" +
                "Fri Jul 19 11:54:00 BRT 2024\n" +
                "ALARM: Wake up!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }

    public void testAlarms() throws InterruptedException {
        alarms = new ArrayList<>();

        String alarm1 = "10:55";
        String textAlarm1 = "You are late!";
        alarms.add(convertHourText(alarm1, textAlarm1));

        String alarm2 = "10:52";
        String textAlarm2 = "Wake up!";
        alarms.add(convertHourText(alarm2, textAlarm2));

        String alarm3 = "10:53";
        String textAlarm3 = "Now!!";
        alarms.add(convertHourText(alarm3, textAlarm3));

        clock = new AlarmClock(alarms);

        String textAlarms =
                "Fri Jul 19 10:52:00 BRT 2024\n" +
                        "ALARM: Wake up!\n" +
                        "Fri Jul 19 10:53:00 BRT 2024\n" +
                        "ALARM: Now!!\n" +
                        "Fri Jul 19 10:54:00 BRT 2024\n" +
                        "Fri Jul 19 10:55:00 BRT 2024\n" +
                        "ALARM: You are late!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }

    public void testCancelAlarms() throws InterruptedException {
        List<List<Object>> alarms = new ArrayList<>();

        String alarm1 = "13:04";
        String textAlarm1 = "Wake up!";
        alarms.add(convertHourText(alarm1, textAlarm1));

        String alarm2 = "13:05";
        String textAlarm2 = "Now!!";
        alarms.add(convertHourText(alarm2, textAlarm2));

        String alarm3 = "13:06";
        String textAlarm3 = "You are late!";
        alarms.add(convertHourText(alarm3, textAlarm3));

        clock = new AlarmClock(alarms);
        clock.cancelAlarm(textAlarm2);
        clock.waitForCompletion();

        String textAlarms =
                        "Fri Jul 19 13:04:00 BRT 2024\n" +
                        "ALARM: Wake up!\n" +
                        "Fri Jul 19 13:05:00 BRT 2024\n" +
                        "ALARM: CANCELED!\n" +
                        "Fri Jul 19 13:06:00 BRT 2024\n" +
                        "ALARM: You are late!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }
}