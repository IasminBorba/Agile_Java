package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.io.IOException;

import static AgileJavaExercises.util.ObjectDumper.dumper;

public class ProxyMyFileTest extends TestCase {
    public void testCreate() throws IllegalAccessException, IOException {
        String filename = "Test for class";
        MyFile myFile = new MyFile(filename);
        String toStringMyFile = dumper(myFile);

        ProxyMyFile proxyMyFile = new ProxyMyFile(filename);
        String toStringProxyMyFile = proxyMyFile.toString();

        assertEquals(toStringMyFile, toStringProxyMyFile);

        String text = "Modifying object";
        myFile.write(text);
        assertEquals(dumper(myFile), proxyMyFile.toString());

        proxyMyFile.overwrite("Modifying different object");
        assertEquals(dumper(myFile), proxyMyFile.toString());

        myFile.delete();
    }
}
