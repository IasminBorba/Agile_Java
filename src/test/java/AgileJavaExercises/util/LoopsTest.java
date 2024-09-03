package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.util.List;
import java.util.Vector;

public class LoopsTest extends TestCase {

    public void testFactorial() {
        assertEquals(1, Loops.testWhile(0));
        assertEquals(1, Loops.testWhile(1));
        assertEquals(2, Loops.testWhile(2));
        assertEquals(6, Loops.testWhile(3));
        assertEquals(24, Loops.testWhile(4));
        assertEquals(120, Loops.testWhile(5));

        assertEquals(1, Loops.testFor(0));
        assertEquals(1, Loops.testFor(1));
        assertEquals(2, Loops.testFor(2));
        assertEquals(6, Loops.testFor(3));
        assertEquals(24, Loops.testFor(4));
        assertEquals(120, Loops.testFor(5));

        assertEquals(1, Loops.testDo(0));
        assertEquals(1, Loops.testDo(1));
        assertEquals(2, Loops.testDo(2));
        assertEquals(6, Loops.testDo(3));
        assertEquals(24, Loops.testDo(4));
        assertEquals(120, Loops.testDo(5));

        assertEquals(1, Loops.testTrue(0));
        assertEquals(1, Loops.testTrue(1));
        assertEquals(2, Loops.testTrue(2));
        assertEquals(6, Loops.testTrue(3));
        assertEquals(24, Loops.testTrue(4));
        assertEquals(120, Loops.testTrue(5));
    }

    public void testContinueControl() {
        assertEquals("1", Loops.continueControl(1));
        assertEquals("1 2 3 4 5*", Loops.continueControl(5));
        assertEquals("1 2 3 4 5* 6 7 8 9 10* 11 12", Loops.continueControl(12));
        assertEquals("1 2 3 4 5* 6 7 8 9 10* 11 12 13 14 15* 16 17 18 19", Loops.continueControl(19));
        assertEquals("1 2 3 4 5* 6 7 8 9 10* 11 12 13 14 15* 16 17 18 19 20* 21 22 23 24 25*", Loops.continueControl(25));
    }

    public void testVectorString() {
        String string1 = Loops.continueControl(1);
        Vector<String> expected = new Vector<>(List.of("1"));
        assertEquals(expected, Loops.vectorString(string1));
        assertEquals(string1, Loops.vectorStringEnumeration(expected));
        assertEquals(string1, Loops.notParameterizedTypes(expected));

        String string2 = Loops.continueControl(5);
        Vector<String> expected2 = new Vector<>(List.of("1", "2", "3", "4", "5*"));
        assertEquals(expected2, Loops.vectorString(string2));
        assertEquals(string2, Loops.vectorStringEnumeration(expected2));
        assertEquals(string2, Loops.notParameterizedTypes(expected2));

        String string3 = Loops.continueControl(12);
        Vector<String> expected3 = new Vector<>(List.of("1", "2", "3", "4", "5*", "6", "7", "8", "9", "10*", "11", "12"));
        assertEquals(expected3, Loops.vectorString(string3));
        assertEquals(string3, Loops.vectorStringEnumeration(expected3));
        assertEquals(string3, Loops.notParameterizedTypes(expected3));

        String string4 = Loops.continueControl(19);
        Vector<String> expected4 = new Vector<>(List.of(
                "1", "2", "3", "4", "5*", "6", "7", "8", "9", "10*",
                "11", "12", "13", "14", "15*", "16", "17", "18", "19"
        ));
        assertEquals(expected4, Loops.vectorString(string4));
        assertEquals(string4, Loops.vectorStringEnumeration(expected4));
        assertEquals(string4, Loops.notParameterizedTypes(expected4));

        String string5 = Loops.continueControl(25);
        Vector<String> expected5 = new Vector<>(List.of(
                "1", "2", "3", "4", "5*", "6", "7", "8", "9", "10*",
                "11", "12", "13", "14", "15*", "16", "17", "18", "19", "20*",
                "21", "22", "23", "24", "25*"
        ));
        assertEquals(expected5, Loops.vectorString(string5));
        assertEquals(string5, Loops.vectorStringEnumeration(expected5));
        assertEquals(string5, Loops.notParameterizedTypes(expected5));
    }
}
