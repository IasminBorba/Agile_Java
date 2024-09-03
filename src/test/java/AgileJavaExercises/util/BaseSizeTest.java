package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;

public class BaseSizeTest extends TestCase {
    public void testBaseSize() throws IOException {
        File file = new File("testTypes.txt");
        BaseSize baseSize = new BaseSize();

        char aChar = 'a';
        byte[] bytes = baseSize.baseTest(file, aChar);
        assertEquals(2, bytes.length);
        assertValue(aChar, bytes);

        int anInt = 5;
        bytes = baseSize.baseTest(file, anInt);
        assertEquals(4, bytes.length);
        assertValue(anInt, bytes);

        double aDouble = 1.5;
        bytes = baseSize.baseTest(file, aDouble);
        assertEquals(8, bytes.length);
        assertValue(aDouble, bytes);

        long aLong = 1000L;
        bytes = baseSize.baseTest(file, aLong);
        assertEquals(8, bytes.length);
        assertValue(aLong, bytes);

        float aFloat = 1.4f;
        bytes = baseSize.baseTest(file, aFloat);
        assertEquals(4, bytes.length);
        assertValue(aFloat, bytes);

        short aShort = 1000;
        bytes = baseSize.baseTest(file, aShort);
        assertEquals(2, bytes.length);
        assertValue(aShort, bytes);

        file.delete();
    }

    public <T> void assertValue(T expectedValue, byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);

        switch (expectedValue.getClass().getSimpleName()) {
            case "Character" -> assertEquals(expectedValue, dis.readChar());
            case "Integer" -> assertEquals(expectedValue, dis.readInt());
            case "Double" -> assertEquals(expectedValue, dis.readDouble());
            case "Long" -> assertEquals(expectedValue, dis.readLong());
            case "Float" -> assertEquals(expectedValue, dis.readFloat());
            case "Short" -> assertEquals(expectedValue, dis.readShort());
        }
    }
}