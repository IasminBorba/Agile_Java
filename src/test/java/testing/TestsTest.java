package testing;

import junit.framework.TestCase;

import java.util.*;

public class TestsTest extends TestCase {
    public void testArray() {
        List<?>[] namesTable = new List<?>[100];
        Object[] objects = namesTable;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        objects[0] = numbers;

        try {
            String name = (String) namesTable[0].getFirst();
        } catch (ClassCastException exception) {
        }
    }
}
