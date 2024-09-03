package AgileJavaExercises.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class Loops {
    public static int testWhile(int n) {
        int factorialN = 1;
        int index = 1;

        if (n == 0 || n == 1)
            return factorialN;
        else if (n > 1)
            while (index < n + 1) {
                factorialN *= index;
                index++;
            }

        return factorialN;
    }

    public static int testFor(int n) {
        int factorialN = 1;

        if (n == 0 || n == 1)
            return factorialN;
        else if (n > 1)
            for (int x = 1; x < n + 1; x++)
                factorialN *= x;

        return factorialN;
    }

    public static int testDo(int n) {
        int factorialN = 1;
        int i = 1;

        do {
            if (n == 0 || n == 1)
                i = n;
            else if (n > 1)
                factorialN *= i;
        } while (++i <= n);

        return factorialN;
    }

    public static int testTrue(int n) {
        int factorialN = 1;
        int i = 1;

        if (n == 0 || n == 1)
            return factorialN;

        while (true) {
            factorialN *= i;
            if (i == n)
                break;
            else
                i++;
        }

        return factorialN;
    }

    public static String continueControl(int n) {
        StringBuilder string = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            String aux = i + "";
            string.append(aux);

            if ((i % 5) == 0)
                string.append("*");
            if (i == n)
                continue;
            string.append(" ");
        }

        return string.toString();
    }

    public static Vector<String> vectorString(String str) {
        Vector<String> vector = new Vector<>();
        Collections.addAll(vector, str.split(" "));

        return vector;
    }

    public static String vectorStringEnumeration(Vector<String> vector) {
        StringBuilder str = new StringBuilder();
        for (Enumeration<String> string = vector.elements(); string.hasMoreElements(); ) {
            str.append(string.nextElement());
            if (!string.hasMoreElements())
                continue;

            str.append(" ");
        }

        return str.toString();
    }

    public static String notParameterizedTypes(Vector<String> vector) {
        StringBuilder str = new StringBuilder();
        Iterator it = vector.iterator();

        while (it.hasNext()) {
            str.append((String) it.next());
            str.append(" ");
        }
        str.deleteCharAt(str.lastIndexOf(" "));

        return str.toString();
    }
}
