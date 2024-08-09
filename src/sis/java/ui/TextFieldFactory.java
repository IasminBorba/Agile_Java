package ui;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

public class TextFieldFactory {
    public static JTextField create(Field fieldSpec) {
        JTextField field;

        if (fieldSpec.getFormat() != null)
            field = createFormattedTextField(fieldSpec);
        else {
            field = new JTextField();
            if (fieldSpec.getInitialValue() != null)
                field.setText(fieldSpec.getInitialValue().toString());
        }

        if (fieldSpec.getLimit() > 0)
            attachLimitFilter(field, fieldSpec.getLimit());

        if (fieldSpec.isUpcaseOnly())
            attachUpcaseFilter(field);

        if(fieldSpec.getName().equals("numberField"))
            attachNumberFilter(field);

        field.setColumns(fieldSpec.getColumns());
        field.setName(fieldSpec.getName());
        return field;
    }

    private static void attachLimitFilter(JTextField field, int limit) {
        attachFilter(field, new LimitFilter(limit));
    }

    private static void attachUpcaseFilter(JTextField field) {
        attachFilter(field, new UpcaseFilter());
    }

    private static void attachNumberFilter(JTextField field) {
        attachFilter(field, new NumberFilter());
    }

    private static void attachFilter(JTextField field, ChainableFilter filter) {
        AbstractDocument document = (AbstractDocument) field.getDocument();
        ChainableFilter existingFilter = (ChainableFilter) document.getDocumentFilter();

        if (existingFilter == null)
            ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
        else
            existingFilter.setNextFilter(filter);
    }

    private static JTextField createFormattedTextField(Field fieldSpec) {
        JFormattedTextField field = new JFormattedTextField(fieldSpec.getFormat());
        field.setValue(fieldSpec.getInitialValue());
        return field;
    }
}
