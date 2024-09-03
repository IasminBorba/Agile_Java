package AgileJavaExercises.util;

import junit.framework.TestCase;

public class WordCountTest extends TestCase {

    public void testCreate() {
        String text = "Create a String literal using the first two sentences of this exercise. " +
                "You will create WordCount class to parse through the text and count the number of instances of each word.";

        WordCount wordCount = new WordCount(text);
        assertEquals(2, wordCount.count("create"));
        assertEquals(3, wordCount.count("the"));
        assertEquals(3, wordCount.count("of"));
        assertEquals(1, wordCount.count("word"));
        assertFalse(wordCount.words.containsKey("word."));
        assertFalse(wordCount.words.containsKey("."));
    }
}
