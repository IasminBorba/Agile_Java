package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class NameTest extends TestCase {
    public void testCreate() {
        Name nameA = new Name("abc");
        Name nameB = new Name("abc");
        Name nameC = new Name("abc");
        Name nameD = new Name("123");

        assertTrue(nameA.equals(nameA));

        assertTrue(nameA.equals(nameB));
        assertTrue(nameB.equals(nameA));

        assertTrue(nameA.equals(nameB));
        assertTrue(nameB.equals(nameC));
        assertTrue(nameA.equals(nameC));

        assertEquals(nameA, nameB);

        assertTrue(!nameA.equals(null));
    }

    public void testSet() {
        Set<Name> nameSet = new HashSet<>();
        nameSet.add(new Name("Foo"));
        nameSet.add(new Name("Foo1"));
        nameSet.add(new Name("Foo2"));

        assertFalse(nameSet.contains(new Name("Foo")));

        Name foo = new Name("Foo");
        nameSet.add(foo);
        assertTrue(nameSet.contains(foo));
    }
}
