package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogFormatterTest extends TestCase {

    public void testCreate() {
        Logger logger = Logger.getLogger(CustomLogFormatter.class.getName());

        CustomHandler countingHandler = new CustomHandler();
        CustomLogFormatter formatter = new CustomLogFormatter(countingHandler);
        countingHandler.setFormatter(formatter);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomLogFormatter(null));  // Sem contagem

        logger.addHandler(countingHandler);
        logger.addHandler(consoleHandler);

        logger.setUseParentHandlers(false);  // Desativar handlers padrão

        logger.log(Level.WARNING, "watch out");
        logger.log(Level.WARNING, "be careful");
        logger.log(Level.INFO, "info message");

        System.out.println("Log Summary:");
        for (Map.Entry<Level, Integer> entry : countingHandler.getLevelCountMap().entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}
