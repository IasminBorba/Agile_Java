package ui;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Field<T> extends JTextField {
    private String fieldName;
    private String text;
    private String shortName;
    private String info;
    private int limit;
    private int columns;
    private boolean upCaseOnly = false;
    private LocalDate date;
    private T value;
    private DateTimeFormatter format;
    private List<String> comboBoxOptions;
    private JComboBox comboBox;

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

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFormat(DateTimeFormatter format) {
        this.format = format;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public String getShortName() {
        return this.shortName;
    }

    public String getInfo() {
        return info;
    }

    public void addComboBoxOptions(String options) {
        comboBoxOptions.add(options);
    }

    public void setComboBoxOptions(List<String> options) {
        this.comboBoxOptions = options;
        if (options != null) {
            this.comboBox = new JComboBox<>(options.toArray());
            comboBox.setName(fieldName);
            comboBox.setEditable(true);
            super.setVisible(false);
            add(comboBox);
        }
    }

    public boolean isComboBox() {
        return comboBoxOptions != null;
    }

    public List<String> getComboBoxOptions() {
        return comboBoxOptions;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    @Override
    public void setText(String text) {
        if (isComboBox()) {
            JComboBox<String> comboBox = (JComboBox<String>) getComponent(0);
            comboBox.setSelectedItem(text);
        } else {
            super.setText(text);
        }
    }

    @Override
    public String getText() {
        if (isComboBox()) {
            JComboBox<String> comboBox = (JComboBox<String>) getComponent(0);
            return (String) comboBox.getSelectedItem();
        }
        return super.getText();
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