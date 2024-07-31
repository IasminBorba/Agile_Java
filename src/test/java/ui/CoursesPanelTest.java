package ui;

import junit.framework.TestCase;
import javax.swing.*;

public class CoursesPanelTest extends TestCase {
    public void testCreate() {
        CoursesPanel panel = new CoursesPanel();
        JLabel label = (JLabel) Util.getComponent(panel, CoursesPanel.LABEL_NAME);
        assertEquals(CoursesPanel.LABEL_TEXT, label.getText());
    }
}
