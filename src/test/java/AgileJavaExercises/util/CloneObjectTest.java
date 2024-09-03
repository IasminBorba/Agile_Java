package AgileJavaExercises.util;

import junit.framework.TestCase;

import static AgileJavaExercises.util.CloneObject.shallowClone;
import static AgileJavaExercises.util.ObjectDumper.dumper;

public class CloneObjectTest extends TestCase {
    public void testCreate() throws Exception {
        ObjectDumper objectDumper = new ObjectDumper();
        Object cloneObjectDumper = shallowClone(objectDumper);

        String stringObjectDumper = dumper(objectDumper);
        String stringCloneObjectDumper = dumper(cloneObjectDumper);
        assertEquals(stringObjectDumper, stringCloneObjectDumper);
    }
}
