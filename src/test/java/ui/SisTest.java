package ui;

import junit.framework.TestCase;
import studentinfo.*;
import util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SisTest extends TestCase {
    private Sis sis;
    private JFrame frame;
    private CoursesPanel panel;
    private Robot robot;

    protected void setUp() throws AWTException {
        sis = new Sis();
        frame = sis.getFrame();
        panel = (CoursesPanel) Util.getComponent(frame, CoursesPanel.NAME);
        robot = new Robot();
    }

    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(Sis.HEIGHT, frame.getSize().getHeight(), tolerance);
        assertEquals(Sis.WIDTH, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertNotNull(Util.getComponent(frame, CoursesPanel.NAME));
        assertEquals(Sis.COURSES_TITLE, frame.getTitle());

        Image image = frame.getIconImage();
        assertEquals(image, ImageUtil.create("images/courses.gif").getImage());
    }

    protected void tearDown() {
        sis.close();
    }

    public void testShow() {
        sis.show();
        assertTrue(frame.isVisible());
    }

    public void testAddCourse() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, true);
        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "MATH");
        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, "300");

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);

        button.doClick();

        Course course = panel.getCourse(0);
        assertEquals("MATH", course.getDepartment());
        assertEquals("300", course.getNumber());
    }

    public void testSetAddButtonState() throws Exception {
        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());

        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "a");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());

        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, "1");
        sis.setAddButtonState();
        assertTrue(button.isEnabled());

        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, " ");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());

        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "a");
        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, " ");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());
    }

    public void testKeyListeners() throws Exception {
        sis.show();
        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());

        // Selecionar o campo de departamento e emular a digitação da letra 'A'
        JTextField departmentField = panel.getField(CoursesPanel.DEPARTMENT_FIELD_NAME);
        type(departmentField, 'A');
        // Selecionar o campo de número e emular a digitação do número '1'
        JTextField numberField = panel.getField(CoursesPanel.NUMBER_FIELD_NAME);
        type(numberField, '1');

        // Verificar se o botão está habilitado
        assertTrue(button.isEnabled());
    }

    private void type(JTextField field, char character) {
        KeyEvent keyEvent = new KeyEvent(field, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, character);
        field.dispatchEvent(keyEvent);
    }
}
