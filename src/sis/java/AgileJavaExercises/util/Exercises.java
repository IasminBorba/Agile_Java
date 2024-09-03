package AgileJavaExercises.util;

import java.util.logging.Logger;

public class Exercises {
    final static Logger logger = Logger.getLogger(Exercises.class.getName());

    public Exercises() {
        String message = "InitializerError";
        Exercises.logger.info(message);
        throw new RuntimeException(message);
    }

    public void blowsUp() {
        throw new RuntimeException("Somebody should catch this!");
    }

    public void rethrows() {
        try {
            blowsUp();
        } catch (Exception e) {
            throw new RuntimeException("Exception Rethrows", e);
        }
    }
}

