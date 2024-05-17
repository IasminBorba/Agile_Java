package exercises;

import junit.framework.TestCase;

public class CharacterTest extends TestCase {
    public void testWhitespace(){
        assertTrue(Character.isWhitespace('\n'));
        assertTrue(Character.isWhitespace('\r'));
        assertTrue(Character.isWhitespace('\t'));
        assertTrue(Character.isWhitespace('\f'));
        assertTrue(Character.isWhitespace(' '));
        assertFalse(Character.isWhitespace('\b'));

        assertFalse(Character.isIdentifierIgnorable('^'));
        assertTrue(Character.isJavaIdentifierPart('x'));
        assertFalse(Character.isJavaIdentifierStart('1'));
    }
}
