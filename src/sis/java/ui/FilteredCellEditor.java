package ui;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FilteredCellEditor extends DefaultCellEditor {
    private static final Map<String, ChainableFilter> FILTERS = new HashMap<>();

    static {
        FILTERS.put("deptField", new UpcaseFilter());
        FILTERS.put("numberField", new NumberFilter());
        FILTERS.put("effectiveDateField", new DateFilter());
    }

    private final JTextField textField;
    private final ChainableFilter filter;
    private final Field field;

    public FilteredCellEditor(String fieldName, Field field) {
        super(new JTextField());
        this.textField = (JTextField) getComponent();
        this.filter = FILTERS.get(fieldName);
        this.field = field;

        if (filter != null) {
            AbstractDocument document = (AbstractDocument) textField.getDocument();
            int limit = field.getLimit();
            if (limit > 0) {
                document.setDocumentFilter(new LimitFilter(limit));
                ((ChainableFilter) document.getDocumentFilter()).setNextFilter(filter);
            }
            else {
                ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
            }
        }
    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        textField.setText(value.toString());
        return textField;
    }

    @Override
    public boolean stopCellEditing() {
        String value = textField.getText();
        return super.stopCellEditing();
    }
}
