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
                field.setText(fieldSpec.getInitialValue().toString());  // Use setText for initial value
        }
        System.out.println("PRIMEIRO");

        if (fieldSpec.getLimit() > 0) {
            attachLimitFilter(field, fieldSpec.getLimit());
        }
        System.out.println("SEGUNDO");

        if (fieldSpec.isUpcaseOnly()) {
            attachUpcaseFilter(field);
        }

        field.setColumns(fieldSpec.getColumns());
        field.setName(fieldSpec.getName());
        System.out.println(field.getName());
        return field;
    }

    private static void attachLimitFilter(JTextField field, int limit) {
        attachFilter(field, new LimitFilter(limit));
    }

    private static void attachUpcaseFilter(JTextField field) {
        attachFilter(field, new UpcaseFilter());
    }

    private static void attachFilter(JTextField field, ChainableFilter filter) {
        AbstractDocument document = (AbstractDocument) field.getDocument();
        ChainableFilter existingFilter = (ChainableFilter) document.getDocumentFilter();
        System.out.println(existingFilter);

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
