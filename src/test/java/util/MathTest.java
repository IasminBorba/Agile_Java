package util;

import junit.framework.TestCase;

import java.util.*;


public class MathTest extends TestCase {
    static final double TOLERANCE = 0.05;

    public void testHypotenuse(){
        assertEquals(5.0, Math.hypotenuse(3.0, 4.0), TOLERANCE);
    }


    public void testClassesWrapper(){
        assertEquals("101", Integer.toBinaryString(5));
        assertEquals("32", Integer.toHexString(50));
        assertEquals("21", Integer.toOctalString(17));
        assertEquals("1022", Integer.toString(35,3));

        assertTrue(253 == Integer.decode("0xFD"));
        assertTrue(253 == Integer.decode("0XFD"));
        assertTrue(253 == Integer.decode("#FD"));
        assertTrue(15 == Integer.decode("017"));
        assertTrue(10 == Integer.decode("10"));
        assertTrue(-253 == Integer.decode("-0xFD"));
        assertTrue(-253 == Integer.decode("-0XFD"));
        assertTrue(-253 == Integer.decode("-#FD"));
        assertTrue(-15 == Integer.decode("-017"));
        assertTrue(-10 == Integer.decode("-10"));
    }

    public void testCoinFlips(){
        final long seed = 1001;
        final int total = 10;

        Random random1 = new Random(seed);
        List<Boolean> flips1 = new ArrayList<>();
        for(int i = 0; i<total; i++){
            flips1.add(random1.nextBoolean());
        }

        Random random2 = new Random(seed);
        List<Boolean> flips2 = new ArrayList<>();
        for (int i=0; i<total; i++){
            flips2.add(random2.nextBoolean());
        }

        assertEquals(flips1, flips2);
    }
}
