package clock;

import junit.framework.TestCase;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.*;

public class AlarmClockTest extends TestCase {
    private AlarmClock clock;
    private Lock lock;
    private Condition exactTime;

    protected void setUp() {
        lock = new ReentrantLock();
        exactTime = lock.newCondition();
    }
    public void testAlarm() throws InterruptedException {
        int hour = 10;
        int minute = 53;
        String textAlarm = "Wake up!";
        ClockListener listener = createClockListener(hour, minute, textAlarm);
        clock = new AlarmClock(listener);
        lock.lock();
        try {
            exactTime.await();
        } finally {
            lock.unlock();
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
                    lock.lock();
                    try {
                        exactTime.signalAll();
                    }
                    finally {
                        lock.unlock();
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
