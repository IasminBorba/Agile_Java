package clock;

import junit.framework.TestCase;
import java.time.LocalDateTime;
import java.util.*;

public class AlarmClockTest extends TestCase {
    private AlarmClock clock;
    private final Object monitor = new Object();

    public void testAlarm() throws InterruptedException {
        int hour = 11;
        int minute = 3;
        String textAlarm = "Wake up!";
        ClockListener listener = createClockListener(hour, minute, textAlarm);
        clock = new AlarmClock(listener);
        synchronized (monitor){
            monitor.wait();
        }
        clock.stop();
        verifyAlarm(hour,minute);
    }

    private ClockListener createClockListener(int hour, int minute, String textAlarm) {
        return new ClockListener() {
            public void update(Date date){
                System.out.println(date);
                if (date.getHours() == hour && date.getMinutes() == minute) {
                    System.out.println(textAlarm);
                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                }
            }
        };
    }

    private void verifyAlarm(int hour, int minute){
        LocalDateTime now = LocalDateTime.now();
        assertEquals(hour, now.getHour());
        assertEquals(minute, now.getMinute());
    }
}
