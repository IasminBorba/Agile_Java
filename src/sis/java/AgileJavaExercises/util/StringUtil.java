package AgileJavaExercises.util;

public class StringUtil {
    public static final String NEWLINE = System.lineSeparator();

    private StringUtil() {
    }

    public static String appendNewLine(String str) {
        return str + NEWLINE;
    }
}