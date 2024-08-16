package ui;

import junit.framework.TestCase;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.*;
import java.text.*;
import java.util.*;

import studentinfo.*;

import static ui.CoursesPanel.*;
import static ui.FieldCatalog.*;

public class CoursesPanelTest extends TestCase {
    private CoursesPanel panel;
    private FieldCatalog catalog;
    private boolean wasClicked;

    protected void setUp() {
        panel = new CoursesPanel();
        catalog = new FieldCatalog();
    }

    public void testCreate() {
        assertEmptyTable(COURSES_TABLE_NAME);
        assertButtonText(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        assertLabelText(DEPARTMENT_FIELD_NAME, DEPARTMENT_LABEL_TEXT);
        assertLabelText(NUMBER_FIELD_NAME, NUMBER_LABEL_TEXT);
        verifyEffectiveDate();

        JButton button = panel.getButton(ADD_BUTTON_NAME);
        assertEquals(ADD_BUTTON_MNEMONIC, button.getMnemonic());
    }

    private void verifyEffectiveDate() {
        assertLabelText(EFFECTIVE_DATE_FIELD_NAME, EFFECTIVE_DATE_LABEL_TEXT);
        JTextField field = panel.getField(EFFECTIVE_DATE_FIELD_NAME);
        if (field instanceof JFormattedTextField dateField) {
            DateFormatter formatter = (DateFormatter) dateField.getFormatter();
            SimpleDateFormat format = (SimpleDateFormat) formatter.getFormat();
            assertEquals("MM/dd/yy", format.toPattern());
            assertEquals(Date.class, dateField.getValue().getClass());
        }
    }

    private void assertEmptyTable(String name) {
        JTable table = panel.getTable(name);
        assertEquals(0, table.getModel().getRowCount());
    }

    private void assertLabelText(String name, String text) {
        JLabel label = panel.getLabel(name);
        assertEquals(text, label.getText());
    }

    private void assertTitleText(String text) {
        String title = panel.getTitle();
        assertEquals(text, title);
    }

    private void assertButtonText(String name, String text) {
        JButton button = panel.getButton(name);
        assertEquals(text, button.getText());
    }

    public void testAddButtonClick() {
        JButton button = panel.getButton(ADD_BUTTON_NAME);

        wasClicked = false;
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wasClicked = true;
            }
        });

        button.doClick();
        assertTrue(wasClicked);
    }

    private void assertFields(String[] fieldNames) {
        StatusBar statusBar = (StatusBar) Util.getComponent(panel, StatusBar.NAME);

        FieldCatalog catalog = new FieldCatalog();
        for (String fieldName : fieldNames) {
            JTextField field = panel.getField(fieldName);
            Field fieldSpec = catalog.get(fieldName);
            assertEquals(fieldSpec.getInfo(), statusBar.getInfo(field));
            assertLabelText(fieldSpec.getName(), fieldSpec.getLabel());
        }
    }

    public void testAddCourseDuplicate() {
        JTable table = panel.getTable(COURSES_TABLE_NAME);
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course = new Course("ENGL", "101");
        panel.addCourse(course);
        assertSame(course, model.get(0));

        Course course2 = new Course("ENGL", "101");
        panel.addCourse(course2);

        assertFalse((model.getCourses().size()) == 2);
    }

    public void testAddCourse() {
        Course course = new Course("ENGL", "101");
        panel.addCourse(course);

        JTable table = panel.getTable(COURSES_TABLE_NAME);
        CoursesTableModel model = (CoursesTableModel) table.getModel();
        assertSame(course, model.get(0));
    }

    public void testClearFields() {
        assertCleanFields();

        String fieldDept = DEPARTMENT_FIELD_NAME;
        panel.setText(fieldDept, "ENGL");
        String textDept = panel.getText(fieldDept);
        assertTrue(panel.getText(fieldDept).equals("ENGL"));

        String fieldNum = NUMBER_FIELD_NAME;
        panel.setText(fieldNum, "101");
        String textNum = panel.getText(fieldNum);
        assertTrue(panel.getText(fieldNum).equals("101"));

        panel.addCourse(new Course(textDept, textNum));
        assertCleanFields();
    }

    void assertCleanFields() {
        for (JTextField fieldText : panel.getFields().values())
            assertEquals("", fieldText.getText());
    }

    public void testEnableDisable() {
        panel.setEnabled(ADD_BUTTON_NAME, true);
        JButton buttonAdd = panel.getButton(ADD_BUTTON_NAME);
        assertTrue(buttonAdd.isEnabled());

        panel.setEnabled(ADD_BUTTON_NAME, false);
        assertFalse(buttonAdd.isEnabled());

        panel.setEnabled(REMOVE_BUTTON_NAME, true);
        JButton buttonRemove = panel.getButton(REMOVE_BUTTON_NAME);
        assertTrue(buttonRemove.isEnabled());

        panel.setEnabled(REMOVE_BUTTON_NAME, false);
        assertFalse(buttonRemove.isEnabled());
    }

    public void testAddListener() throws Exception {
        KeyListener listener = new KeyAdapter() {
        };
        panel.addFieldListener("deptField", listener);
        JTextField field = panel.getField(DEPARTMENT_FIELD_NAME);
        KeyListener[] listeners = field.getKeyListeners();
        assertEquals(1, listeners.length);
        assertSame(listener, listeners[0]);
    }
}