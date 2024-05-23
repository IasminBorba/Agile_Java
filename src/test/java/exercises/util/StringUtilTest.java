package exercises.util;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {
    public void testCreate(){
        assertEquals("test\n", StringUtil.appendNewLine("test"));
    }
}
