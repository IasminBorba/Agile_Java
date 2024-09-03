package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.util.ArrayList;

public class ToStringerTest extends TestCase {
    public void testCreate() throws Exception {
        Dir dir = new Dir("test");

        ToStringer toStr = new ToStringer(dir);

        String boardStr = "Board fields annotation @Dump:\n" +
                "\tfilename: null\n" +
                "\tpiecesOnTheBoard: \n" +
                "\tpieces: []\n" +
                "\tboard: null\n" +
                "\tpiecesBlack: 0\n" +
                "\tfile: null\n" +
                "\tpiecesWhite: 0";

        assertEquals(boardStr, toStr.toString(dir));
    }

    public void testOrder() throws Exception {
        DefaultDumpFieldTest dumpFieldTest = new DefaultDumpFieldTest();
        ToStringer tStr = new ToStringer(dumpFieldTest);

        String defaultDumpStr = "DefaultDumpFieldTest fields annotation @Dump:\n" +
                "\ttestArrayList: null\n" +
                "\ttestBoolean: null\n" +
                "\ttestString: null\n" +
                "\ttestDoubleDump: 0.0\n" +
                "\ttestFloatDump: 0.0\n" +
                "\ttestStringNotDump: null\n" +
                "\ttestIntegerNotDump: null";

        assertEquals(defaultDumpStr, tStr.toString(dumpFieldTest));
    }

    public void testQuote() throws Exception {
        DefaultDumpQuoteTest dumpQuoteTest = new DefaultDumpQuoteTest();
        ToStringer tStr = new ToStringer(dumpQuoteTest);

        String defaultDumpStr = "DefaultDumpQuoteTest fields annotation @Dump:\n" +
                "\ttestArrayList: null\n" +
                "\ttestString: null\n" +
                "\ttestDoubleDumpQuote: \"0.0\"\n" +
                "\ttestPrivateBoolean: null\n" +
                "\ttestPrivateFloatQuote: \"0.0\"\n" +
                "\ttestIntegerDumpQuote: \"null\"\n" +
                "\ttestStringNotDump: null";

        assertEquals(defaultDumpStr, tStr.toString(dumpQuoteTest));
    }

    public void testOutputMethod() throws Exception {
        DefaultDumpToStringTest dumpToStringTest = new DefaultDumpToStringTest(new FullName());
        ToStringer tStr = new ToStringer(dumpToStringTest);

        String defaultDumpStr = "DefaultDumpToStringTest fields annotation @Dump:\n" +
                "\tfullNameCustom: Borba, Iasmin\n" +
                "\tfullName: Iasmin Borba";

        assertEquals(defaultDumpStr, tStr.toString(dumpToStringTest));
    }

    public void testOutputMethodException() throws Exception {
        DefaultDumpExceptionTest dumpExceptionTest = new DefaultDumpExceptionTest(new FullName());
        ToStringer tStr = new ToStringer(dumpExceptionTest);

        String defaultDumpStr = tStr.toString(dumpExceptionTest);
    }

    public void testOutputMethods() throws Exception {
        DefaultDumpOutputMethodsListTest dumpTest = new DefaultDumpOutputMethodsListTest(new DefaultDumpOutputMethodsListTest.Person());
        ToStringer tStr = new ToStringer(dumpTest);

        String defaultDumpStr = "DefaultDumpOutputMethodsListTest fields annotation @Dump:\n" +
                "\tperson: My name is Iasmin Borba\n" +
                "\tI'm 22 years old\n" +
                "\tMy fone: (99) 99999-9999";

        assertEquals(defaultDumpStr, tStr.toString(dumpTest));
    }
}

class DefaultDumpFieldTest {
    @Dump(order = 3)
    public String testString;
    public String testStringNotDump;
    public Integer testIntegerNotDump;
    @Dump(order = 1)
    public ArrayList<String> testArrayList;
    @Dump
    private float testFloatDump;
    @Dump(order = 2)
    private Boolean testBoolean;
    @Dump
    public double testDoubleDump;
}

class DefaultDumpQuoteTest {
    @Dump(order = 3)
    public String testString;
    public String testStringNotDump;
    @Dump(quote = true)
    private Integer testIntegerDumpQuote;
    @Dump(order = 1)
    public ArrayList<String> testArrayList;
    @Dump(quote = true)
    private float testPrivateFloatQuote;
    @Dump
    private Boolean testPrivateBoolean;
    @Dump(quote = true)
    public double testDoubleDumpQuote;
}

class DefaultDumpToStringTest {
    @Dump(outputMethods = "customOutPut")
    public FullName fullNameCustom;
    public FullName fullName;

    public DefaultDumpToStringTest(FullName fullName) {
        this.fullNameCustom = fullName;
        this.fullName = fullName;
    }
}

class FullName {
    public String firstName = "Iasmin";
    public String lastName = "Borba";

    public String customOutPut() {
        return lastName + ", " + firstName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}

class DefaultDumpExceptionTest {
    @Dump(outputMethods = "customNotExists")
    public FullName fullNameException;
    public FullName fullName;

    public DefaultDumpExceptionTest(FullName fullName) {
        this.fullNameException = fullName;
        this.fullName = fullName;
    }
}

class DefaultDumpOutputMethodsListTest {
    @Dump(outputMethods = {"toStringName", "toStringAge", "toStringFone"})
    public Person person;

    public DefaultDumpOutputMethodsListTest(Person person) {
        this.person = person;
    }

    static class Person {
        public String firstName = "Iasmin";
        public String lastName = "Borba";
        public int Age = 22;
        public String Fone = "(99) 99999-9999";

        public String toStringName() {
            return "My name is " + firstName + " " + lastName;
        }

        public String toStringAge() {
            return "I'm " + Age + " years old";
        }

        public String toStringFone() {
            return "My fone: " + Fone;
        }
    }
}