package clock;

import junit.framework.TestCase;

public class AlarmClockTest extends TestCase {
    private AlarmClock clock;

    public void testAlarm() throws InterruptedException {
        clock = new AlarmClock();
        clock.addAlarm("14:02", "Wake up!");

        clock.waitForCompletion();

        String textAlarms =
                "Fri Jul 19 14:01:00 BRT 2024\n" +
                        "Fri Jul 19 14:02:00 BRT 2024\n" +
                        "ALARM: Wake up!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }

    public void testAlarms() throws InterruptedException {
        clock = new AlarmClock();

        String alarm1 = "14:06";
        String textAlarm1 = "You are late!";
        clock.addAlarm(alarm1, textAlarm1);

        String alarm2 = "14:03";
        String textAlarm2 = "Wake up!";
        clock.addAlarm(alarm2, textAlarm2);

        String alarm3 = "14:04";
        String textAlarm3 = "Now!!";
        clock.addAlarm(alarm3, textAlarm3);

        clock.waitForCompletion();

        String textAlarms =
                "Fri Jul 19 14:03:00 BRT 2024\n" +
                        "ALARM: Wake up!\n" +
                        "Fri Jul 19 14:04:00 BRT 2024\n" +
                        "ALARM: Now!!\n" +
                        "Fri Jul 19 14:05:00 BRT 2024\n" +
                        "Fri Jul 19 14:06:00 BRT 2024\n" +
                        "ALARM: You are late!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }

    public void testCancelAlarms() throws InterruptedException {
        clock = new AlarmClock();

        String alarm1 = "14:07";
        String textAlarm1 = "Wake up!";
        clock.addAlarm(alarm1, textAlarm1);

        String alarm2 = "14:08";
        String textAlarm2 = "Now!!";
        clock.addAlarm(alarm2, textAlarm2);

        String alarm3 = "14:09";
        String textAlarm3 = "You are late!";
        clock.addAlarm(alarm3, textAlarm3);

        clock.cancelAlarm(textAlarm2);

        clock.waitForCompletion();

        String textAlarms =
                "Fri Jul 19 14:07:00 BRT 2024\n" +
                        "ALARM: Wake up!\n" +
                        "Fri Jul 19 14:08:00 BRT 2024\n" +
                        "ALARM: CANCELED!\n" +
                        "Fri Jul 19 14:09:00 BRT 2024\n" +
                        "ALARM: You are late!\n";
        assertEquals(textAlarms, clock.printAlarms());
    }
}