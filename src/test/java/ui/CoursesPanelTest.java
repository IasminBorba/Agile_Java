package ui;

import junit.framework.TestCase;
import javax.swing.*;
import java.awt.*;

public class CoursesPanelTest extends TestCase {
    public void testCreate() {
        CoursesPanel panel = new CoursesPanel();
        JLabel label = getLabel(panel);
        assertEquals(CoursesPanel.LABEL_TEXT, label.getText());
    }

    private JLabel getLabel(JPanel panel) {
        for(Component component: panel.getComponents())
            if(component instanceof JLabel)
                return (JLabel) component;
        return null;
    }
}
