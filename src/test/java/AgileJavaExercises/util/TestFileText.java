package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.io.*;
import java.util.ArrayList;

public class TestFileText extends TestCase {
    public void testFile() throws Exception {
        final String filename = "testFileText.txt";

        try {
            try (Writer bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
                bufferedWriter.write(text());
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                assertEqualsText(text(), reader);
            }
        } finally {
            delete(filename);
        }
    }

    public void delete(String filename) {
        File file = new File(filename);
        if (file.exists())
            file.delete();
    }

    public void assertEqualsText(String expectedText, BufferedReader reader) throws IOException {
        StringBuilder buffer = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
            buffer.append(String.format(line + "%n"));
        assertEquals(expectedText, buffer.toString());

        ArrayList<String> expectedQuests = quests(expectedText);
        ArrayList<String> actualQuests = quests(buffer.toString());

        assertEquals(expectedQuests, actualQuests);
        assertEquals(expectedQuests.getFirst(), actualQuests.getFirst());
        assertTrue(actualQuests.contains(expectedQuests.getFirst()));

        String quest4 = "4. Further adventures in utility classes: Create a Dir class that encapsulates a File object that " +
                "in turn represents an actual file system directory. Design the class so that it is functional " +
                "only when mapped to an existing directory. Provide a method named ensureExists to " +
                "create the directory if it does not exist. The constructor should throw an exception if the " +
                "directory name is the same as an existing file. Finally, provide a method that returns a list " +
                "of MyFile objects in the directory and throws an exception if the directory has not been " +
                "created yet.";

        assertEquals(quest4, expectedQuests.get(3));
        assertTrue(quest4.equals(expectedQuests.get(3)) && quest4.equals(actualQuests.get(3)));
        assertEquals(expectedQuests.size(), actualQuests.size());
    }

    public ArrayList<String> quests(String text) {
        ArrayList<String> listQuests = new ArrayList<>();

        for (String str : text.split("\n"))
            if (str != null && !str.equals("Exercises"))
                listQuests.add(str);

        return listQuests;
    }

    public String text() {
        return "Exercises\n" +
                "1. Create a test to write the text of this exercise to the file system. The test should read the " +
                "file back in and make assertions about the content. Ensure that you can run the test " +
                "multiple times and have it pass. Finally, make sure that there are no leftover files when the " +
                "test finishes, even if an exception is thrown.\n" +
                "2. (hard) Create a timing test to prove that using a Buffered class is important for " +
                "performance. The test can loop through various sizes of file, creating character data in " +
                "sizes growing by a factor of 10, calling a method coded to write using the basic character-" +
                "at-a-time methods, then wrapping the writer in a buffered output stream and writing it " +
                "again until a 5x performance gain is reached. What is the threshold at which a buffered " +
                "writer is a significant performance gain?\n" +
                "3. Create and test a utility class called MyFile. This class should wrap a File object, taking a " +
                "string filename as its constructor argument. It should have methods for retrieving the " +
                "content of the file as a String or as a List of lines. It should also have a method for writing " +
                "either a String or a List of Strings. Read and write operations should encapsulate opening " +
                "and closing the fileclients should not have to close the file themselves. " +
                "Ensure that the read methods fail with a specific unchecked exception type if the file " +
                "doesn't exist. Similarly, the write methods should fail if the file does exist. Provide delete " +
                "and overwrite methods, and you will have built a well-tested utility class that you can place " +
                "in your toolbox.\n" +
                "4. Further adventures in utility classes: Create a Dir class that encapsulates a File object that " +
                "in turn represents an actual file system directory. Design the class so that it is functional " +
                "only when mapped to an existing directory. Provide a method named ensureExists to " +
                "create the directory if it does not exist. The constructor should throw an exception if the " +
                "directory name is the same as an existing file. Finally, provide a method that returns a list " +
                "of MyFile objects in the directory and throws an exception if the directory has not been " +
                "created yet.\n" +
                "5. Code a test that shows the use of a ByteArrayOutputStream to capture an exception and " +
                "dump the stack trace into a string. Code it with and without an OutputStreamWriter. In both " +
                "the character version and the byte version, use buffered readers and writers.\n" +
                "6. Modify the chess application to allow you to save the board positions to a text file and read " +
                "them back in. Provide two choicesa serialized object of type Board or a textual " +
                "representation as shown in the earlier exercises.\n" +
                "7. In Additional Lesson III, you will learn the preferred Java technique for cloning, or creating " +
                "a copy of, an object. Until you learn this technique, you can implement cloning using object " +
                "serialization and deserialization to duplicate objects. Your implementation will be a \"poorman's\"" +
                "clone that you should not use in production systems.\n" +
                "8. Create an instance inner class for Dir, named Attributes, that encapsulates two directory " +
                "attributes: Is the directory read-only and is it hidden? The Dir class should return an " +
                "instance of this object if requested. Demonstrate (through failure to compile) that the test " +
                "cannot instantiate this object.\n" +
                "9. Change the Dir.Attributes inner class to a static nested class and change the code and test " +
                "so they still work. What are the implications? Show that the test can instantiate a " +
                "Dir.Attributes class. Does this design make sense?\n" +
                "10. In the exercises for Lesson 10, you wrote code to programmatically determine the size of " +
                "each primitive integral type. Now, write code to determine the base size of all primitive " +
                "types by using a data stream.\n";
    }
}
