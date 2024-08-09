package ui;

import javax.swing.text.*;

public class LimitFilter extends ChainableFilter {
    private final int limit;

    public LimitFilter(int limit) {
        this.limit = limit;
    }

    public void insertString(FilterBypass bypass, int offset, String str, AttributeSet attrSet) throws BadLocationException {
        int newLength = bypass.getDocument().getLength() + str.length();
        if (newLength <= limit)
            applyNextInsert(bypass, offset, str, attrSet);
        else {
            String newString = str.substring(0, limit - bypass.getDocument().getLength());
            applyNextInsert(bypass, offset, newString, attrSet);
        }
    }

    public void replace(FilterBypass bypass, int offset, int length, String str, AttributeSet attrSet) throws BadLocationException {
        int newLength = bypass.getDocument().getLength() - (length) + str.length();
        if (newLength <= limit)
            applyNextReplace(bypass, offset, length, str, attrSet);
        else {
            String newString = str.substring(0, limit - offset);
            applyNextReplace(bypass, offset, length, newString, attrSet);
        }
    }

    public int getLimit() {
        return limit;
    }
}