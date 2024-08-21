package ui;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FieldCatalog {
    public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    static final String DEPARTMENT_FIELD_NAME = "deptField";
    static final String DEPARTMENT_LABEL_TEXT = "Department";
    static final String DEPARTMENT_SHORT_NAME = "Dept";
    static final String DEPARTMENT_FIELD_INFO = "Enter a 4-character department designation.";
    static final int DEPARTMENT_FIELD_LIMIT = 4;
    static final String[] DEPARTMENT_LIST = {"ENGL", "MATH", "CMSC", "NURS", "ARTH"};

    static final String NUMBER_FIELD_NAME = "numberField";
    static final String NUMBER_LABEL_TEXT = "Number";
    static final String NUMBER_SHORT_NAME = "#";
    static final String NUMBER_FIELD_INFO = "The department number should be 3 digits.";
    static final int NUMBER_FIELD_LIMIT = 3;
    static final String[] NUMBER_LIST = {"101", "202", "300", "111", "222"};

    static final String EFFECTIVE_DATE_FIELD_NAME = "effectiveDateField";
    static final String EFFECTIVE_DATE_LABEL_TEXT = "Effective Date";
    static final String EFFECTIVE_DATE_SHORT_NAME = "Eff. Date";
    static final String EFFECTIVE_DATE_FIELD_INFO = "Effective date should be in mm/dd/yy format.";

    static final int DEFAULT_COLUMNS = 20;

    protected Map<String, Field> fields;

    public FieldCatalog() {
        loadFields();
    }

    public int size() {
        return fields.size();
    }

    private void loadFields() {
        fields = new HashMap<>();

        Field fieldSpec = new Field(DEPARTMENT_FIELD_NAME);
        fieldSpec.setLabel(DEPARTMENT_LABEL_TEXT);
        fieldSpec.setShortName(DEPARTMENT_SHORT_NAME);
        fieldSpec.setLimit(DEPARTMENT_FIELD_LIMIT);
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        fieldSpec.setUpCaseOnly();
        fieldSpec.setInfo(DEPARTMENT_FIELD_INFO);
        fieldSpec.setComboBoxOptions(List.of(DEPARTMENT_LIST));

        put(fieldSpec);

        fieldSpec = new Field(NUMBER_FIELD_NAME);
        fieldSpec.setLabel(NUMBER_LABEL_TEXT);
        fieldSpec.setShortName(NUMBER_SHORT_NAME);
        fieldSpec.setLimit(NUMBER_FIELD_LIMIT);
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        fieldSpec.setInfo(NUMBER_FIELD_INFO);
        fieldSpec.setComboBoxOptions(List.of(NUMBER_LIST));

        put(fieldSpec);

        fieldSpec = new Field(EFFECTIVE_DATE_FIELD_NAME);
        fieldSpec.setLabel(EFFECTIVE_DATE_LABEL_TEXT);
        fieldSpec.setShortName(EFFECTIVE_DATE_SHORT_NAME);
        fieldSpec.setFormat(DEFAULT_DATE_FORMAT);
        fieldSpec.setInitialValue(LocalDate.of(2024, 10, 1));
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        fieldSpec.setInfo(EFFECTIVE_DATE_FIELD_INFO);

        put(fieldSpec);
    }

    private void put(Field fieldSpec) {
        fields.put(fieldSpec.getName(), fieldSpec);
    }

    public Field get(String fieldName) {
        return fields.get(fieldName);
    }
}
