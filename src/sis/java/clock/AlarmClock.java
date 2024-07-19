package clock;

import java.time.*;
import java.util.*;

public class AlarmClock implements Runnable{
    private final ClockListener alarm;
    private boolean run = true;
    private final List<List<Object>> alarms;
    private static final Object monitor = new Object();
    private static final StringBuilder textAlarms = new StringBuilder();

    public AlarmClock() throws InterruptedException {
        this.alarms = new ArrayList<>();
        this.alarm = createClockListener();
        new Thread(this).start();
    }

    public void stop(){
        run = false;
    }

    public void run(){
        LocalDateTime lastTime = LocalDateTime.now();
        title();
        while (run){
            try {Thread.sleep(500); }
            catch (InterruptedException e){}
            LocalDateTime now = LocalDateTime.now();
            if(lastTime.getHour() == now.getHour()){
                if((now.getMinute()) - (lastTime.getMinute()) >= 1) {
                    alarm.update(new Date());
                    lastTime = now;
                }
            }
        }
    }

    public void waitForCompletion() throws InterruptedException {
        synchronized (monitor) {
            while (run) {
                monitor.wait();
            }
        }
    }

    private ClockListener createClockListener() {
        return new ClockListener() {
            int count = 0;
            public void update(Date date){
                addText(date);

                int hourNow = date.getHours();
                int minuteNow = date.getMinutes();
                for(List<Object> list: alarms) {
                    if (hourNow == (Integer) list.getFirst() && minuteNow == (Integer) list.get(1)){
                        addText(list.getLast());
                        count++;
                    }
                }

                if (count == alarms.size()) {
                    synchronized (monitor) {
                        run = false;
                        monitor.notifyAll();
                    }
                }
            }
        };
    }

    public static List<Object> convertHourText(String time, String text) {
        int hour = 0;
        int minute = 0;
        int count = 0;

        for(String str: time.split(":")) {
            if(++count == 1)
                hour = Integer.parseInt(str);
            else
                minute = Integer.parseInt(str);
        }
        return Arrays.asList(hour, minute, text);
    }

    public void cancelAlarm(String textAlarm) {
        synchronized (alarms) {
            for (List<Object> alarm : alarms) {
                if (alarm.get(2).equals(textAlarm)) {
                    alarm.set(2, "CANCELED!");
                }
            }
        }
    }

    private void title(){
        StringBuilder builder = new StringBuilder("Alarm List: ");

        sortAlarmList();
        for(List<Object> list: alarms){
            int hour = (Integer)list.getFirst();
            String hourString = (hour < 10) ? "0" + hour : Integer.toString(hour);
            int minute = (Integer)list.get(1);
            String minuteString = (minute < 10) ? "0" + minute : Integer.toString(minute);

            builder.append(hourString).append(":").append(minuteString).append("h, ");
        }
        builder.delete(builder.length() - 2, builder.length());
        System.out.println(builder.append("\n"));
    }

    public void sortAlarmList(){
        alarms.sort(new Comparator<>() {
            @Override
            public int compare(List<Object> alarm1, List<Object> alarm2) {
                int hour1 = (Integer) alarm1.get(0);
                int minute1 = (Integer) alarm1.get(1);
                int hour2 = (Integer) alarm2.get(0);
                int minute2 = (Integer) alarm2.get(1);

                int hourCompare = Integer.compare(hour1, hour2);
                if (hourCompare != 0) {
                    return hourCompare;
                }

                return Integer.compare(minute1, minute2);
            }
        });
    }

    private void addText(Object obj) {
        if (obj instanceof Date) {
            textAlarms.append(obj).append("\n");
            System.out.println(obj);
        } else {
            textAlarms.append("ALARM: ").append(obj).append("\n");
            System.out.println("ALARM: " + obj + "\n");
        }
    }

    protected void addAlarm(String time, String textAlarm) {
        alarms.add(convertHourText(time, textAlarm));
    }

    public String printAlarms(){
        return textAlarms.toString();
    }
}