package AgileJavaExercises.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CustomHandler extends Handler {
    public void flush() {
    }

    public void close() {
    }

    private final Map<Level, Integer> messagesLogged = new HashMap<>();

    public CustomHandler() {
        messagesLogged.put(Level.SEVERE, 0);
        messagesLogged.put(Level.WARNING, 0);
        messagesLogged.put(Level.INFO, 0);
        messagesLogged.put(Level.CONFIG, 0);
        messagesLogged.put(Level.FINE, 0);
        messagesLogged.put(Level.FINER, 0);
        messagesLogged.put(Level.FINEST, 0);
    }

    @Override
    public void publish(LogRecord record) {
        Level level = record.getLevel();
        messagesLogged.put(level, messagesLogged.getOrDefault(level, 0) + 1);
    }

    public int getCount(Level level) {
        return messagesLogged.getOrDefault(level, 0);
    }

    public Map<Level, Integer> getLevelCountMap() {

        return messagesLogged;
    }
}