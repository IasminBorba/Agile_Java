package ui;

import javax.swing.text.*;

public class UpcaseFilter extends ChainableFilter {
    public void insertString(FilterBypass bypass, int offset, String text, AttributeSet attr) throws BadLocationException {
        applyNextInsert(bypass, offset, text.toUpperCase(), attr);
    }

    public void replace(FilterBypass bypass, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
        applyNextReplace(bypass, offset, length, text.toUpperCase(), attr);
    }
}
