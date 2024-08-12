package ui;

import junit.framework.TestCase;

import javax.swing.text.*;

public class NumberFilterTest extends TestCase {
    private DocumentFilter filter;
    protected DocumentFilter.FilterBypass bypass;
    protected AbstractDocument document;

    protected void setUp() {
        bypass = createBypass();
        document = (AbstractDocument) bypass.getDocument();
    }

    public void testInsert() throws BadLocationException {
        filter = new NumberFilter();
        filter.insertString(bypass, 0, "a", null);
        assertEquals("", documentText());

        filter.insertString(bypass, 0, "123", null);
        assertEquals("123", documentText());

        filter.insertString(bypass, 0, "B", null);
        assertEquals("123", documentText());

        filter.insertString(bypass, 3, "45", null);
        assertEquals("12345", documentText());

        filter.insertString(bypass, 5, "123 ", null);
        assertEquals("12345", documentText());
    }

    public void testReplace() throws BadLocationException {
        filter = new NumberFilter();
        filter.insertString(bypass, 0, "1", null);
        filter.replace(bypass, 0, 1, "a", null);
        assertEquals("1", documentText());

        filter.insertString(bypass, 1, "123", null);
        filter.replace(bypass, 0, 1, "T", null);
        assertEquals("1123", documentText());
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
                } catch (BadLocationException e) {
                }
            }

            public void remove(int offset, int length) throws BadLocationException {
            }

            public void replace(int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
                try {
                    document.replace(offset, length, string, attrs);
                } catch (BadLocationException e) {
                }
            }
        };
    }
}
