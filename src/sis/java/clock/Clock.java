package clock;

import java.time.LocalDateTime;

public class Clock implements Runnable {
    private final ClockListener listener;
    private boolean run = true;
    long lastTime;

    public Clock(ClockListener listener) {
        this.listener = listener;
        new Thread(this).start();
    }

    public void stop() {
        run = false;
    }

    public void run() {
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY - 1);
        lastTime = System.currentTimeMillis();
        while (run) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            checkSeconds();
        }
    }

    private void checkSeconds() {
        long now = System.currentTimeMillis();
        if((now / 1000) - (lastTime / 1000) >= 1)
            updateTime(now);
    }

    private void updateTime(long now) {
        listener.update(LocalDateTime.now());
        lastTime = now;
    }
}
