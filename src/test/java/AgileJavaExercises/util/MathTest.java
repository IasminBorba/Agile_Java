package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.rint;
import static java.lang.Math.sqrt;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MathTest extends TestCase {
    public void testBigDecimal() {
        BigDecimal testBigDecimal = new BigDecimal("5.00");

        testBigDecimal.add(new BigDecimal("1.00"));
        assertEquals(new BigDecimal("5.00"), testBigDecimal);
    }

    public void testBigDecimal2() {
        BigDecimal testBigDecimal = new BigDecimal("10.00");
        BigDecimal testBigDecimal2 = new BigDecimal("1");
        assertNotEquals(testBigDecimal2, testBigDecimal);

        BigDecimal bigDecimalScale = (testBigDecimal2).multiply(new BigDecimal("10.00"));
        assertEquals(testBigDecimal, bigDecimalScale);

        BigDecimal bigDecimalNotPrime = new BigDecimal("10").divide(bigDecimalScale);
        assertEquals(testBigDecimal2, bigDecimalNotPrime);
    }

    public void testFloat() {
        float number1 = 0.9f;
        float number2 = 0.005f * 2.0f;
        assertNotEquals(number1, number2);

        float precisionFloat = 0.9f;
        double precisionDouble = 0.9;
        assertEquals(number1, number2, precisionFloat);
        assertEquals(number1, number2, precisionDouble);

        double number12 = 0.9;
        double number22 = 0.005 * 2.0;
        assertEquals(number1, number2, precisionFloat);
        assertEquals(number1, number2, precisionDouble);
    }

    public static class CompilerError {
        //        float x = 0.01;  ERROR
        float x = 0.01f;
        float y = (float) 0.01;
    }

    public void testSpikeDecimalValue() {
        String strValue = "0xDEAD";

        int valueDecimal = Integer.decode("0xDEAD");
        assertEquals(57005, valueDecimal);

        int valueDecimal2 = Math.hexDecimal("0xDEAD");
        assertEquals(57005, valueDecimal2);


        String valueOctal = new BigInteger(String.valueOf(valueDecimal)).toString(8);
        assertEquals("157255", valueOctal);
    }

    public void testNaNInfinity() {
        //Usamdp float, mas tudo se aplica pra o tipo Double;

        assertEquals(Float.NaN, (0.0f / 0));
        assertEquals(Float.NaN, (float) sqrt(-1));
        assertEquals(Float.NaN, (10.0f % 0.0f));
        assertEquals(Float.NaN, Float.NaN * 1f);
        assertEquals(Float.NaN, Float.NaN / 1f);
        assertEquals(Float.NaN, Float.NaN + 1f);
        assertEquals(Float.NaN, Float.NaN - 1f);
        assertEquals(Float.NaN, Float.NaN * Float.NaN);
        assertEquals(Float.NaN, Float.NaN / Float.NaN);
        assertEquals(Float.NaN, Float.NaN + Float.NaN);
        assertEquals(Float.NaN, Float.NaN - Float.NaN);


        assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY);
        assertEquals(Float.POSITIVE_INFINITY, (1.0f / 0));

        assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY - Float.POSITIVE_INFINITY);
        assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY + Float.NEGATIVE_INFINITY);
        assertEquals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY * Float.NEGATIVE_INFINITY);
        assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY * Float.POSITIVE_INFINITY);
        assertEquals(Float.NEGATIVE_INFINITY, (-1.0f / 0));


        assertEquals(Float.NaN, Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY);
        assertEquals(Float.NaN, Float.NEGATIVE_INFINITY - Float.NEGATIVE_INFINITY);
        assertEquals(Float.NaN, Float.POSITIVE_INFINITY - Float.POSITIVE_INFINITY);
        assertEquals(Float.NaN, Float.NEGATIVE_INFINITY + Float.POSITIVE_INFINITY);
        assertEquals(Float.NaN, Float.POSITIVE_INFINITY / Float.NEGATIVE_INFINITY);
        assertEquals(Float.NaN, Float.NEGATIVE_INFINITY / Float.POSITIVE_INFINITY);
        assertEquals(Float.NaN, Float.POSITIVE_INFINITY * 0);
        assertEquals(Float.NaN, Float.NEGATIVE_INFINITY * 0);
    }

    public void testIntegerDivision() {
        Set<Integer> listNumber = new HashSet<>(asList(7, 15, 20, 249, 75, 3, 10));
        List<Integer> number3 = new ArrayList<>(asList(3, 249, 75, 15));

        List<Integer> result1 = Math.divisibleBy3(listNumber);
        assertEquals(number3, result1);

        List<Integer> result2 = Math.divisibleBy3Plus(listNumber);
        assertEquals(number3, result2);
    }

    public void testRounding() {
        int number11 = (int) 1.9;
        System.out.println(number11);

        double number12 = rint(1.9);
        System.out.println(number12);

        double numberA13 = rint(1.5);
        System.out.println(numberA13);
        double numberB13 = rint(2.5);
        System.out.println(numberB13);
        double numberC13 = rint(3.5);
        System.out.println(numberC13);
        double numberD13 = rint(4.5);
        System.out.println(numberD13);
    }

    public void testExpressions() {
        int x = 5;
        int y = 10;
        double numberA = x * 5 + (double) y++ * 7 / 4;
        System.out.println("numberA = " + numberA + "\nx = " + x + "\ny = " + y);

        x = 5;
        y = 10;
        int numberB = ++x * 5 * y++;
        System.out.println("numberB = " + numberB + "\nx = " + x + "\ny = " + y);

        x = 5;
        y = 10;
        int numberC = x++ * 5 * ++y;
        System.out.println("numberC = " + numberC + "\nx = " + x + "\ny = " + y);

        x = 5;
        y = 10;
        int numberD = ++x + 5 * 7 + y++;
        System.out.println("numberD = " + numberD + "\nx = " + x + "\ny = " + y);

//        int numberE = ++ y++ % ++x++;
        x = 5;
        y = 10;

        x++;
        y++;
        // 11 ++ % 6++
        int numberE = y++ % x++;
        System.out.println("numberE = " + numberE + "\nx = " + x + "\ny = " + y);

//        int numberF = x * 7 == 35 || y++ == 0;
        x = 5;
        y = 10;
//        // 5*7 = 35  || 10 != 0
        boolean numberF = ((x * 7 == 35) || y++ == 0);
        System.out.println("numberF = " + numberF + "\nx = " + x + "\ny = " + y);

        x = 5;
        y = 10;
        int numberG = ++x * ++y;
        System.out.println("numberG = " + numberG + "\nx = " + x + "\ny = " + y);

        x = 5;
        y = 10;
        int numberH = x++ * y++;
        System.out.println("numberH = " + numberH + "\nx = " + x + "\ny = " + y);

//        true && x * 7
        x = 5;
        y = 10;
        // true and 5 * 7 = 35
        boolean numberI = true && (x * 7 == 35);
        System.out.println("numberI = " + numberI + "\nx = " + x + "\ny = " + y);


//        x * 2 == y || ++y == 10
        x = 5;
        y = 10;
        // x * 2 = 10 = y
        // 11 != 10
        boolean numberJ = (x * 2 == y || ++y == 10);
        System.out.println("numberJ = " + numberJ + "\nx = " + x + "\ny = " + y);
        x = 5;
        y = 10;
        boolean numberK = (x * 2 == y && ++y == 10);
        System.out.println("numberK = " + numberK + "\nx = " + x + "\ny = " + y);
    }

    public void testOperator() {
        assertEquals(34, 17 << 1);

        // 1 == 0000_0000_0000_0000_0000_0000_0000_0001
        // ~1 == 1111_1111_1111_1111_1111_1111_1111_1110 == 4294967294 == -2
        assertEquals(-2, ~1);


        //10 = 0000_0000_0000_0000_0000_0000_0000_1010
        //5 = 0000_0000_0000_0000_0000_0000_0000_0101
        assertEquals(5, 10 >> 1);


        //1111_1111_1111_1111_1111_1111_1111_0110 = -10
        //1111_1111_1111_1111_1111_1111_1111_1011 = -5
        assertEquals(-5, -10 >> 1);


        //0000_0000_0000_0000_0000_0000_0000_1010 = 10
        //0000_0000_0000_0000_0000_0000_0000_0101 = 5
        assertEquals(5, 10 >>> 1);

        //1111_1111_1111_1111_1111_1111_1111_0110 = -10
        // 0111_1111_1111_1111_1111_1111_1111_1011 = 2147483643
        assertEquals(2147483643, -10 >>> 1);
    }

    public void testRandom() {
        int number = Math.random(50, 1);
        assertTrue(number > 0 && number <= 50);

        ArrayList<Integer> list = new ArrayList<>(asList(7, 15, 20, 249, 75, 3, 10));
        int listSize = list.size();
        for (int x = 0; x < 100; x++) {
            int index = Math.random(listSize, 1);
            list.set(index, Math.random(100, 1));
        }

        assertTrue(list.size() == listSize);
        assertTrue(!list.equals(new ArrayList<>(asList(7, 15, 20, 249, 75, 3, 10))));

        Random random = new Random();
        assertNotEquals(random.nextDouble(1), random.nextDouble());
        System.out.println(random.nextDouble(1));
        System.out.println(random.nextDouble());
    }

    public void testXor() {
        int number1 = 5;
        int number2 = 7;

        number1 = number1 ^ number2;
        number2 = number1 ^ number2;
        assertEquals(5, number2);

        number1 = number2 ^ number1;
        assertEquals(7, number1);
    }

    public void testBitCount() {
        // char(16), byte(8), short(16), int(32), long(64)
        assertEquals(16, Math.bitCount((long) Character.MAX_VALUE) - 1);
        assertEquals(8, Math.bitCount((long) Byte.MAX_VALUE));
        assertEquals(16, Math.bitCount((long) Short.MAX_VALUE));
        assertEquals(32, Math.bitCount((long) Integer.MAX_VALUE));
        assertEquals(64, Math.bitCount(Long.MAX_VALUE));
    }
}
