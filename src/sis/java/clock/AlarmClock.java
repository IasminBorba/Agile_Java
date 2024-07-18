package clock;

import java.time.*;
import java.util.*;

public class AlarmClock implements Runnable{
    private final ClockListener alarm;
    private boolean run = true;

    public AlarmClock(ClockListener alarm){
        this.alarm = alarm;
        new Thread(this).start();
    }

    public void stop(){
        run = false;
    }

    public void run(){
        LocalDateTime lastTime = LocalDateTime.now();
        while (run){
            try {Thread.sleep(1000); }
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
}
