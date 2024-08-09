package ui;

import javax.swing.text.*;

public class NumberFilter extends ChainableFilter {
    public void insertString(FilterBypass bypass, int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str.matches("\\d*"))
            applyNextInsert(bypass, offset, str, attr);
    }

    public void replace(FilterBypass bypass, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
        if (text.matches("\\d*"))
            applyNextReplace(bypass, offset, length, text, attr);
    }
}
