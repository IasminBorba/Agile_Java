package AgileJavaExercises.util;

import junit.framework.TestCase;

public class CustomHandlerTest extends TestCase {
    public void testBadlyFormattedName() {
        CustomHandler handler = new CustomHandler();
        Exercises.logger.addHandler(handler);
        try {
            new Exercises();
            fail("expected exception from in initializer");
        } catch (RuntimeException expectedException) {
            String message = "Deu ruim";
            System.out.println(handler.getLevelCountMap());
        }
    }
}
