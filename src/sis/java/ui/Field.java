package ui;

import javax.swing.*;
import java.text.DateFormat;

public class Field <T> extends JTextField {
    private String fieldName;
    private String text;
    private int limit;
    private int columns;
    private boolean upCaseOnly = false;
    private T date;
    private DateFormat format;

    public Field(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setLabel(String text) {
        this.text = text;
        this.setText(text);
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void setColumns(int columns) {
        super.setColumns(columns);
        this.columns = columns;
    }

    public void setUpCaseOnly() {
        this.upCaseOnly = true;
    }

    public void setInitialValue(T value) {
        this.date = value;
    }

    public void setFormat(DateFormat format) {
        this.format = format;
    }

    public String getLabel() {
        return text;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    public boolean isUpcaseOnly() {
        return upCaseOnly;
    }

    public DateFormat getFormat() {
        return format;
    }

    public T getInitialValue() {
        return date;
    }

    @Override
    public String getName() {
        return fieldName;
    }

    @Override
    public void setName(String fieldName) {
        this.fieldName = fieldName;
        super.setName(fieldName);
    }
}