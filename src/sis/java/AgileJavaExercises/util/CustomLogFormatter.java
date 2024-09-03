package AgileJavaExercises.util;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {
    private final CustomHandler countingLogHandler;

    public CustomLogFormatter(CustomHandler countingLogHandler) {
        this.countingLogHandler = countingLogHandler;
    }

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        Level level = record.getLevel();

        sb.append(level).append(": ").append(record.getMessage());
        if (countingLogHandler != null) {
            int count = countingLogHandler.getCount(level);
            sb.append(" (").append(level).append(" total = ").append(count).append(")");
        }

        sb.append("\n");
        return sb.toString();
    }
}
