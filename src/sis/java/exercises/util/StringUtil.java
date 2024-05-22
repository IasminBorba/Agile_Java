package exercises.util;

public class StringUtil {
    public static final String NEWLINE = System.lineSeparator();

    private StringUtil() {}

    public static String appendNewLine(String s) {
        return s + NEWLINE;
    }
}
