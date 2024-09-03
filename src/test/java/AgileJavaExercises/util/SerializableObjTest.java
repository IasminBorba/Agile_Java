package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;

import static AgileJavaExercises.util.SerializableObj.serializableObjCopy;

public class SerializableObjTest extends TestCase {
    public void testCreate() throws IOException, ClassNotFoundException {
        SerializableObj obj1 = new SerializableObj("name", 10.5, 123);
        File file = new File("SerializableObjTest.txt");

        SerializableObj obj2 = obj1.copyObj(file.getPath());
        SerializableObj obj3 = serializableObjCopy(obj1);

        assertEquals("name", obj2.getName());
        assertEquals(10.5, obj2.getValor());
        assertEquals(123, obj2.getSeq());

        assertEquals("name", obj3.getName());
        assertEquals(10.5, obj3.getValor());
        assertEquals(123, obj3.getSeq());

        file.delete();
    }

}
