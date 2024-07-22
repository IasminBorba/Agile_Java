package clock;

import junit.framework.TestCase;

public class AlarmClockTest extends TestCase {
    private AlarmClock clock;
    public void testAlarmHour() throws InterruptedException {
        clock = new AlarmClock();

        clock.addAlarm("09:00", "Wake up!");

        clock.waitForCompletion();

        String textAlarms =
                        "Mon Jul 22 08:59:00 BRT 2024\n" +
                        "Mon Jul 22 09:00:00 BRT 2024\n" +
                        "ALARM: Wake up!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }

    public void testAlarm() throws InterruptedException {
        clock = new AlarmClock();

        clock.addAlarm("09:02", "Wake up!");

        clock.waitForCompletion();

        String textAlarms =
                        "Mon Jul 22 09:01:00 BRT 2024\n" +
                        "Mon Jul 22 09:02:00 BRT 2024\n" +
                        "ALARM: Wake up!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }

    public void testAlarms() throws InterruptedException {
        clock = new AlarmClock();

        String alarm1 = "09:06";
        String textAlarm1 = "You are late!";
        clock.addAlarm(alarm1, textAlarm1);

        String alarm2 = "09:03";
        String textAlarm2 = "Wake up!";
        clock.addAlarm(alarm2, textAlarm2);

        String alarm3 = "09:04";
        String textAlarm3 = "Now!!";
        clock.addAlarm(alarm3, textAlarm3);

        clock.waitForCompletion();

        String textAlarms =
                        "Mon Jul 22 09:03:00 BRT 2024\n" +
                        "ALARM: Wake up!\n" +
                        "Mon Jul 22 09:04:00 BRT 2024\n" +
                        "ALARM: Now!!\n" +
                        "Mon Jul 22 09:05:00 BRT 2024\n" +
                        "Mon Jul 22 09:06:00 BRT 2024\n" +
                        "ALARM: You are late!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }

    public void testCancelAlarms() throws InterruptedException {
        clock = new AlarmClock();

        String alarm1 = "09:12";
        String textAlarm1 = "Wake up!";
        clock.addAlarm(alarm1, textAlarm1);

        String alarm2 = "09:13";
        String textAlarm2 = "Now!!";
        clock.addAlarm(alarm2, textAlarm2);

        String alarm3 = "09:14";
        String textAlarm3 = "You are late!";
        clock.addAlarm(alarm3, textAlarm3);

        clock.cancelAlarm(textAlarm2);

        clock.waitForCompletion();

        String textAlarms =
                        "Mon Jul 22 09:12:00 BRT 2024\n" +
                        "ALARM: Wake up!\n" +
                        "Mon Jul 22 09:13:00 BRT 2024\n" +
                        "ALARM: CANCELED!\n" +
                        "Mon Jul 22 09:14:00 BRT 2024\n" +
                        "ALARM: You are late!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }
}