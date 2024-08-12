package clock;

import junit.framework.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class ClockTest extends TestCase {
    private Clock clock;
    private Lock lock;
    private Condition receivedEnoughTics;

    protected void setUp() {
        lock = new ReentrantLock();
        receivedEnoughTics = lock.newCondition();
    }

    public void testClock() throws Exception {
        final int seconds = 10;
        final List<LocalDateTime> tics = new ArrayList<>();
        ClockListener listener = createClockListener(tics, seconds);
        clock = new Clock(listener);
        lock.lock();
        try {
            receivedEnoughTics.await();
        } finally {
            lock.unlock();
        }
        clock.stop();
        verify(tics, seconds);
    }

    private ClockListener createClockListener(final List<LocalDateTime> tics, final int seconds) {
        return new ClockListener() {
            private int count = 0;

            public void update(LocalDateTime date) {
                tics.add(date);
                if (++count == seconds) {
                    lock.lock();
                    try {
                        receivedEnoughTics.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }

    private void verify(List<LocalDateTime> tics, int seconds) {
        assertEquals(seconds, tics.size());
        for (int i = 1; i < seconds; i++)
            assertEquals(1, getSecondsFromLast(tics, i));
    }

    private long getSecondsFromLast(List<LocalDateTime> tics, int i) {
        LocalDateTime now = tics.get(i);
        LocalDateTime then = tics.get(i - 1);
        Duration duration = Duration.between(then, now);

        long seconds = duration.getSeconds();
        long nanos = duration.getNano();

        if (nanos >= 500_000_000)
            seconds += 1;

        return seconds;
    }
}
