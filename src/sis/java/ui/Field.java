package ui;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Field<T> extends JTextField {
    private String fieldName;
    private String text;
    private int limit;
    private int columns;
    private boolean upCaseOnly = false;
    private LocalDate date;
    private T value;
    private DateTimeFormatter format;

    public Field(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setLabel(String text) {
        this.text = text;
        super.setText(text);
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

    public void setInitialValue(T values) {
        if (values != null && values.getClass().equals(LocalDate.class))
            this.date = (LocalDate) values;
        else
            this.value = values;
    }

    public void setFormat(DateTimeFormatter format) {
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

    public DateTimeFormatter getFormat() {
        return format;
    }

    public T getInitialValue() {
        if (value != null && value.getClass().equals(DateTimeFormatter.class))
            return (T) date.format(format);
        return value;
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