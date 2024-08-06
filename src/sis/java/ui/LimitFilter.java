package ui;

import javax.swing.text.*;

public class LimitFilter extends DocumentFilter {
    private final int limit;

    public LimitFilter(int limit) {
        this.limit = limit;
    }

    public void insertString(DocumentFilter.FilterBypass bypass, int offset, String str, AttributeSet attrSet) throws BadLocationException {
        replacse(bypass, offset, 0, str, attrSet);
    }

    public void replacse(DocumentFilter.FilterBypass bypass, int offset, int length, String str, AttributeSet attrSet) throws BadLocationException {
        int newLength = bypass.getDocument().getLength() - length + str.length();
        if(newLength > limit)
            throw new BadLocationException("New characters exceeds max size of document", offset);
        bypass.replace(offset, length, str, attrSet);
    }
}