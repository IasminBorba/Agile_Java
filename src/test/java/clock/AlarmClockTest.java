package clock;

import junit.framework.TestCase;
import java.time.LocalDateTime;
import java.util.*;

public class AlarmClockTest extends TestCase {
    private AlarmClock clock;
    private final Object monitor = new Object();
//ClockListener listener;
    public void testAlarm() throws InterruptedException {
        int hour = 11;
        int minute = 49;
        ArrayList<List<Integer>> alarms = new ArrayList<>();
//        for(int i = 1; i < 3; i++)
//            alarms.add(List.of(hour, minute+i));
         alarms.add(List.of(hour, minute+1));
         alarms.add(List.of(hour, minute+3));
        System.out.println("Alarm List:  " + alarms + "\n");

        String textAlarm = "Wake up!\n";
        ClockListener listener = createClockListener(alarms, textAlarm);
        clock = new AlarmClock(listener);
        synchronized (monitor){
            monitor.wait();
        }
        clock.stop();
//        verifyAlarm(alarms, listener);
    }

    private ClockListener createClockListener(ArrayList<List<Integer>> alarms, String textAlarm) {
        return new ClockListener() {
            int count = 1;
            public void update(Date date){
                System.out.println(date);
                if (alarms.contains(List.of(date.getHours(), date.getMinutes()))){
                    System.out.println(textAlarm);
                } else if (count == alarms.size()) {
                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                }
                count++;
            }
        };
    }

    private void verifyAlarm(int hour, int minute){
        LocalDateTime now = LocalDateTime.now();
        assertEquals(hour, now.getHour());
        assertEquals(minute, now.getMinute());
        //for(alarm: alarms)
    }
}
