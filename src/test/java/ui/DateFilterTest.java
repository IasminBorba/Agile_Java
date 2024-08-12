package ui;

import junit.framework.TestCase;

import javax.swing.text.*;

public class DateFilterTest extends TestCase {
    private DocumentFilter filter;
    protected DocumentFilter.FilterBypass bypass;
    protected AbstractDocument document;
    String date = "01/01/24";

    protected void setUp() {
        bypass = createBypass();
        document = (AbstractDocument) bypass.getDocument();
        filter = new DateFilter();
    }

    public void testInsert() throws BadLocationException {
        filter.insertString(bypass, 0, date, null);
        assertEquals("01/01/2024", documentText());

        filter.insertString(bypass, 1, "05/05/24", null);
        assertEquals("01/01/2024", documentText());

        setUp();
        filter.insertString(bypass, 0, "def", null);
        assertEquals("", documentText());

        filter.insertString(bypass, 0, "010505", null);
        assertEquals("", documentText());

        filter.insertString(bypass, 0, "21/12/24", null);
        assertEquals("", documentText());

        filter.insertString(bypass, 0, "09/08/", null);
        filter.insertString(bypass, 0, "24", null);
        assertEquals("", documentText());
    }

    public void testReplace() throws BadLocationException {
        filter.insertString(bypass, 0, "09/08/24", null);
        filter.replace(bypass, 0, 2, "tc", null);
        assertEquals("09/08/2024", documentText());

        filter.replace(bypass, 0, 2, "30", null);
        assertEquals("12/08/2024", documentText());

        filter.replace(bypass, 0, 2, "12", null);
        assertEquals("12/08/2024", documentText());

        filter.replace(bypass, 3, 2, "32", null);
        assertEquals("12/30/2024", documentText());

        filter.replace(bypass, 8, 2, "25", null);
        assertEquals("12/30/2025", documentText());

        filter.replace(bypass, 6, 2, "10", null);
        assertEquals("12/30/1025", documentText());

        filter.replace(bypass, 2, 1, "A", null);
        assertEquals("12/30/1025", documentText());

        filter.replace(bypass, 0, 1, "?", null);
        assertEquals("12/30/1025", documentText());
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