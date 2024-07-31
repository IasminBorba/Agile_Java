package ui;

import junit.framework.TestCase;
import javax.swing.*;
import static ui.CoursesPanel.*;

public class CoursesPanelTest extends TestCase {
    public void testCreate() {
        CoursesPanel panel = new CoursesPanel();
        JLabel label = (JLabel) Util.getComponent(panel, LABEL_NAME);
        assertEquals(LABEL_TEXT, label.getText());

        JList list = (JList) Util.getComponent(panel, COURSES_LIST_NAME);
        assertEquals(0, list.getModel().getSize());

        JButton button = (JButton) Util.getComponent(panel, ADD_BUTTON_NAME);
        assertEquals(ADD_BUTTON_TEXT, button.getText());

        JLabel departmentLabel = (JLabel)Util.getComponent(panel, DEPARTMENT_LABEL_NAME);
        assertEquals(DEPARTMENT_LABEL_TEXT, departmentLabel.getText());

        JTextField departmentField = (JTextField)Util.getComponent(panel, DEPARTMENT_FIELD_NAME);
        assertEquals("", departmentField.getText());

        JLabel numberLabel = (JLabel) Util.getComponent(panel, NUMBER_LABEL_NAME);
        assertEquals(NUMBER_LABEL_TEXT, numberLabel.getText());

        JTextField numberField = (JTextField) Util.getComponent(panel, NUMBER_FIELD_NAME);
        assertEquals("", numberField.getText());
    }
}