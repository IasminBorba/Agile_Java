package AgileJavaExercises.util;

import java.util.HashMap;
import java.util.Map;

public class WordCount {
    Map<String, Integer> words = new HashMap<>();

    WordCount(String text) {
        for (String word : text.split(" ")) {
            word = word.replaceAll("\\.$", "").toLowerCase();
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
                continue;
            }
            words.put(word, 1);
        }
    }

    public int count(String str) {
        return words.get(str);
    }
}