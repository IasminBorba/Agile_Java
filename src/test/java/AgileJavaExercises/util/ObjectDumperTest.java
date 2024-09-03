package AgileJavaExercises.util;

import junit.framework.TestCase;

import static AgileJavaExercises.util.ObjectDumper.dumper;

public class ObjectDumperTest extends TestCase {
    public void testObjectDumper() throws IllegalAccessException {
        String builderExpected = "Class ObjectDumper:\n" +
                "\t[PUBLIC] Array 1D of int - testArray1: {1, 2, 3} \n" +
                "\t[PUBLIC] Array 1D of String - testArrayString:\n" +
                "\t\ttest1;\ttest2;\ttest3;\n" +
                "\t[] Array 2D of float - testArray2:\n" +
                "\t\t1.0;\t1.0\n" +
                "\t\t2.0;\t2.0;\n" +
                "\t[] Array 2D of Float - testArrayFloat:\n" +
                "\t\t11.0\n" +
                "\t\t22.0;\n" +
                "\t[] Array 3D of String - testArray3:\n" +
                "\t\tTest1.1;\tTest1.2\n" +
                "\t\tTest2.1;\n" +
                "\t[] Array 4D of String - testArray4:\n" +
                "\t\tTest1;\ttest1.2\n" +
                "\t\ttest2.2\n" +
                "\t\tteste\n" +
                "\t\ttest123;\tteste\n" +
                "\t\ttest5432;\n" +
                "\t[] Map - map: {map3=3, map2=2, map1=1}\n" +
                "\t[] StringBuffer - buffer: \n" +
                "\t\ttestBuffer";

        String stringObjectDumper = dumper(new ObjectDumper());
        assertEquals(builderExpected, stringObjectDumper);
    }
}
