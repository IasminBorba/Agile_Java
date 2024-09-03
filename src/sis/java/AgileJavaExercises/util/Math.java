package AgileJavaExercises.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.lang.Math.pow;

public class Math {
    public static int hexDecimal(String hex) {
        int size = hex.length() - 2;
        int result = 0;

        for (int i = 2; i < hex.length(); i++) {
            switch (hex.charAt(i)) {
                case '1':
                    result += (1 * pow(16, --size));
                    break;
                case '2':
                    result += (2 * pow(16, --size));
                    break;
                case '3':
                    result += (3 * pow(16, --size));
                    break;
                case '4':
                    result += (4 * pow(16, --size));
                    break;
                case '5':
                    result += (5 * pow(16, --size));
                    break;
                case '6':
                    result += (6 * pow(16, --size));
                    break;
                case '7':
                    result += (7 * pow(16, --size));
                    break;
                case '8':
                    result += (8 * pow(16, --size));
                    break;
                case '9':
                    result += (9 * pow(16, --size));
                    break;
                case 'A':
                    result += (10 * pow(16, --size));
                    break;
                case 'B':
                    result += (11 * pow(16, --size));
                    break;
                case 'C':
                    result += (12 * pow(16, --size));
                    break;
                case 'D':
                    result += (13 * pow(16, --size));
                    break;
                case 'E':
                    result += (14 * pow(16, --size));
                    break;
                case 'F':
                    result += (15 * pow(16, --size));
            }
        }

        return result;
    }

    public static List<Integer> divisibleBy3(Set<Integer> listNumbers) {
        List<Integer> result = new ArrayList<>();
        for (int number : listNumbers)
            if (number % 3 == 0)
                result.add(number);

        return result;
    }

    public static List<Integer> divisibleBy3Plus(Set<Integer> listNumbers) {
        List<Integer> result = new ArrayList<>();
        boolean permission = true;
        for (int number : listNumbers) {
            int aux = number / 3;
            if (aux * 3 == number)
                result.add(number);
        }

        return result;
    }

    public static int random(int limit, int initial) {
        Random random = new Random();
        return initial + random.nextInt(limit - 1);
    }

    public static int bitCount(Long value) {
        int bits = 0;
        while (value != 0) {
            value >>= 1;
            bits++;
        }
        bits++;

        return bits;
    }
}
