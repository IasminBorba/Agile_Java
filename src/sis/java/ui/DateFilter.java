package ui;

import javax.swing.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFilter extends ChainableFilter {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public void insertString(FilterBypass bypass, int offset, String text, AttributeSet attr) throws BadLocationException {
        String newText = getTextFromDocument(bypass).substring(0, offset) + text + getTextFromDocument(bypass).substring(offset);
        try {
            LocalDate date = LocalDate.parse(newText, INPUT_FORMATTER);
            String formattedDate = date.format(DISPLAY_FORMATTER);
            applyNextInsert(bypass, offset, formattedDate, attr);
        } catch (DateTimeParseException e) {
            applyNextInsert(bypass, offset, "", attr);
        }
    }

    @Override
    public void replace(FilterBypass bypass, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
        String doc = getTextFromDocument(bypass);
        String newText = getTextFromDocument(bypass).substring(0, offset) + text + getTextFromDocument(bypass).substring(offset + length);

        try {
            LocalDate date = LocalDate.parse(newText, INPUT_FORMATTER);
            String formattedDate = date.format(DISPLAY_FORMATTER);
            applyNextReplace(bypass, 0, doc.length(), formattedDate, attr);
        } catch (DateTimeParseException e) {
            if (text.matches("\\d*") && newText.length() < 11) {
                applyNextReplace(bypass, offset, length, text, attr);

                if (newText.length() == 2) {
                    applyNextReplace(bypass, offset + 1, length, "/", attr);
                    verifyDate(newText, bypass, attr);
                }

                if (newText.length() == 5) {
                    applyNextReplace(bypass, offset + 1, length, "/", attr);
                    verifyDate(newText, bypass, attr);
                }
                verifyDate(newText, bypass, attr);
            } else {
                applyNextInsert(bypass, offset, "", attr);
            }
        }
    }

    private String getTextFromDocument(FilterBypass bypass) throws BadLocationException {
        return bypass.getDocument().getText(0, bypass.getDocument().getLength());
    }

    private void verifyDate(String text, FilterBypass bypass, AttributeSet attr) throws BadLocationException {
        try {
            if (Integer.parseInt(text.substring(0, 2)) > 12)
                applyNextReplace(bypass, 0, 3, "12/", attr);

            if (Integer.parseInt(text.substring(3, 5)) > 31)
                applyNextReplace(bypass, 3, 3, "30/", attr);
        } catch (Exception e) {}
    }
}
