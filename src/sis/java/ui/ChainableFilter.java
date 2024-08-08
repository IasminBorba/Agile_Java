package ui;

import javax.swing.text.*;

public abstract class ChainableFilter extends DocumentFilter {
    protected ChainableFilter nextFilter;

    public void setNextFilter(ChainableFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    protected DocumentFilter getNextFilter() {
        return nextFilter;
    }

    public ChainableFilter getNext() {
        return nextFilter;
    }

    protected void applyNextInsert(FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
        if (nextFilter != null) {
            nextFilter.insertString(fb, offset, str, attr);
        } else {
            super.insertString(fb, offset, str, attr);
        }
    }

    protected void applyNextReplace(FilterBypass fb, int offset, int length, String text, AttributeSet attrSet) throws BadLocationException {
        if (nextFilter != null)
            nextFilter.replace(fb, offset, length, text, attrSet);
        else
            super.replace(fb, offset, length, text, attrSet);
    }
}
