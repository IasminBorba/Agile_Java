package util;

import junit.framework.TestCase;

public class InfinityTest extends TestCase {
    public void testInfinity() {
        final float tolerance = 0.5f;
        final float x = 1f;

        assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY * 100, tolerance);
        assertEquals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY * (-1), tolerance);

        assertEquals(Float.POSITIVE_INFINITY, x / 0f, tolerance);
        assertEquals(Float.NEGATIVE_INFINITY, x / -0f, tolerance);
        assertTrue(Float.isNaN(x % 0f));

        assertEquals(0f, x / Float.POSITIVE_INFINITY, tolerance);
        assertEquals(-0f, x / Float.NEGATIVE_INFINITY, tolerance);
        assertEquals(x, x % Float.POSITIVE_INFINITY, tolerance);

        assertTrue(Float.isNaN(0f / 0f));
        assertTrue(Float.isNaN(0f % 0f));

        assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY / x, tolerance);
        assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY / x, tolerance);
        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % x));

        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY / Float.POSITIVE_INFINITY));
        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % Float.POSITIVE_INFINITY));
        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY / Float.NEGATIVE_INFINITY));
        assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % Float.NEGATIVE_INFINITY));

        assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY / Float.POSITIVE_INFINITY));
        assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY % Float.POSITIVE_INFINITY));
        assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY / Float.NEGATIVE_INFINITY));
        assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY % Float.NEGATIVE_INFINITY));
    }
}
