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
        createAlarm(alarm, textAlarm);

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
        createAlarm(alarm1, textAlarm1);

        String alarm2 = "10:52";
        String textAlarm2 = "Wake up!";
        createAlarm(alarm2, textAlarm2);

        String alarm3 = "10:53";
        String textAlarm3 = "Now!!";
        createAlarm(alarm3, textAlarm3);

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

    public void createAlarm(String alarm, String textAlarm){
        alarms.add(convertHourText(alarm, textAlarm));
    }
}