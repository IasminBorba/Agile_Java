package util;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.*;

public class StringUtilTest extends TestCase {
    private static final String TEXT = "this is it, isn't it";

    public void testOccurrencesOne() {
        assertEquals(1, StringUtil.occurrences(TEXT, "his"));
    }

    public void testOccurrencesNone() {
        assertEquals(0, StringUtil.occurrences(TEXT, "smelt"));
    }

    public void testOccurrencesMany() {
        assertEquals(3, StringUtil.occurrences(TEXT, "is"));
        assertEquals(2, StringUtil.occurrences(TEXT, "it"));
    }

    public void testOccurrencesSearchStringTooLarge() {
        assertEquals(0, StringUtil.occurrences(TEXT, TEXT + "sdfas"));
    }

    public void testConcatenateList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        String output = StringUtil.concatenate(list);
        assertEquals(String.format("a%nb%n"), output);
    }

    public void testConcatenateFormattedDecimals() {
        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("3.1416"));
        list.add(new BigDecimal("-1.4142"));

        String output = StringUtil.concatenateNumbers(list, 3);
        assertEquals(String.format("3.142%n-1.414%n"), output);
    }

    public void testCOncatenateFormattedIntegers() {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(17);

        String output = StringUtil.concatenateNumbers(list, 0);
        assertEquals(String.format("12%n17%n"), output);
    }
}
