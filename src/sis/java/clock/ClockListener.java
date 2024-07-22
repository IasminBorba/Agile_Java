package clock;

import java.time.LocalDateTime;

public interface ClockListener {
    void update(LocalDateTime date);
}
