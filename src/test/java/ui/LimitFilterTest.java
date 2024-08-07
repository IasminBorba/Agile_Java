package ui;

import junit.framework.TestCase;

import javax.swing.text.*;

public class LimitFilterTest extends TestCase {
    private DocumentFilter filter;
    protected DocumentFilter.FilterBypass bypass;
    protected AbstractDocument document;

    protected void setUp() {
        bypass = createBypass();
        document = (AbstractDocument)bypass.getDocument();
    }

    public void testInsert() throws BadLocationException {
        filter = new LimitFilter(5);
        filter.insertString(bypass, 0, "abc", null);
        assertEquals("abc", documentText());

        filter.insertString(bypass, 0, "H", null);
        assertEquals("Habc", documentText());

        filter.insertString(bypass, 3, "DEF", null);
        assertEquals("HabDc", documentText());
    }

    public void testReplace() throws BadLocationException {
        filter = new LimitFilter(3);
        filter.insertString(bypass, 0, "XYZ", null);
        filter.replace(bypass,1,2,"ab", null);
        assertEquals("Xab", documentText());

        filter.replace(bypass, 0, 3, "TEST", null);
        assertEquals("TES", documentText());

        filter = new LimitFilter(6);
        filter.replace(bypass, 2, 1, "12345", null);
        assertEquals("TE1234", documentText());

        filter.replace(bypass, 0, 6, "HELLO", null);
        assertEquals("HELLO", documentText());
    }

    protected String documentText() throws BadLocationException {
        return document.getText(0, document.getLength());
    }

    protected DocumentFilter.FilterBypass createBypass() {
        return new DocumentFilter.FilterBypass() {
            private final AbstractDocument document = new PlainDocument();

            public Document getDocument() {
                return document;
            }

            public void insertString(int offset, String string, AttributeSet attr) {
                try {
                    document.insertString(offset, string, attr);
                } catch (BadLocationException e) {}
            }

            public void remove(int offset, int length) throws BadLocationException {}

            public void replace(int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
                try {
                    document.replace(offset, length, string, attrs);
                } catch (BadLocationException e) {}
            }
        };
    }
}
