package studentinfo;

import junit.framework.TestCase;

public class NameTest extends TestCase {
    public void testExtraneousWhitespace() {
        final String fullName = "Jeffrey\t\t\n \r\fHyman";
        Name name = createName(fullName);
        assertEquals("Jeffrey", name.firstName);
        assertEquals("Hyman", name.lastName);
    }

    private Name createName(String fullName) {
        Name name = new Name(fullName);
        assertEquals(fullName, name.fullName);
        return name;
    }
}
