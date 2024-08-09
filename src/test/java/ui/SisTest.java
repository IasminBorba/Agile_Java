package ui;

import junit.framework.TestCase;
import studentinfo.*;
import util.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class SisTest extends TestCase {
    private Sis sis;
    private JFrame frame;
    private CoursesPanel panel;
    private Robot robot;
    private FieldCatalog catalog;

    protected void setUp() throws AWTException {
        sis = new Sis();
        frame = sis.getFrame();
        panel = (CoursesPanel) Util.getComponent(frame, CoursesPanel.NAME);
        robot = new Robot();
        catalog = new FieldCatalog();
    }

    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(Sis.HEIGHT, frame.getSize().getHeight(), tolerance);
        assertEquals(Sis.WIDTH, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertNotNull(Util.getComponent(frame, CoursesPanel.NAME));
        assertEquals(Sis.COURSES_TITLE, frame.getTitle());

        assertNotNull(panel);

        Image image = frame.getIconImage();
        assertEquals(image, ImageUtil.create("images/courses.gif").getImage());

        verifyFilters(panel);
    }

    private void verifyFilters(CoursesPanel panel) {
        ArrayList<DocumentFilter> filterDep = getFilters(panel, catalog.DEPARTMENT_FIELD_NAME);
        assertTrue(filterDep.getFirst().getClass() == LimitFilter.class);
        assertTrue(filterDep.get(1).getClass() == UpcaseFilter.class);

        ArrayList<DocumentFilter> filter = getFilters(panel, catalog.NUMBER_FIELD_NAME);
        assertTrue(filter.getFirst().getClass() == LimitFilter.class);
        assertTrue(filter.get(1).getClass() == NumberFilter.class);
    }

    private ArrayList<DocumentFilter> getFilters(CoursesPanel panel, String fieldName) {
        ArrayList<DocumentFilter> listFilter = new ArrayList<>();
        JTextField field = panel.getField(fieldName);
        AbstractDocument document = (AbstractDocument) field.getDocument();
        DocumentFilter currentFilter = document.getDocumentFilter();

        while (currentFilter != null) {
            listFilter.add(currentFilter);
            if (currentFilter instanceof ChainableFilter chainableFilter)
                currentFilter = chainableFilter.getNextFilter();
            else
                break;
        }
        return listFilter;
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
        panel.setText(FieldCatalog.DEPARTMENT_FIELD_NAME, "MATH");
        panel.setText(FieldCatalog.NUMBER_FIELD_NAME, "300");

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);

        button.doClick();

        Course course = panel.getCourse(0);
        assertEquals("MATH", course.getDepartment());
        assertEquals("300", course.getNumber());
    }

    public void testSetAddButtonState() throws Exception {
        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());

        panel.setText(FieldCatalog.DEPARTMENT_FIELD_NAME, "a");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());

        panel.setText(FieldCatalog.NUMBER_FIELD_NAME, "1");
        sis.setAddButtonState();
        assertTrue(button.isEnabled());

        panel.setText(FieldCatalog.DEPARTMENT_FIELD_NAME, " ");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());

        panel.setText(FieldCatalog.DEPARTMENT_FIELD_NAME, "a");
        panel.setText(FieldCatalog.NUMBER_FIELD_NAME, "123");
        sis.setAddButtonState();
        assertTrue(button.isEnabled());
    }

//    public void testKeyListeners() throws Exception {
//        sis.show();
//        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
//        assertFalse(button.isEnabled());
//
//        selectField(CoursesPanel.DEPARTMENT_FIELD_NAME);
//        type(KeyEvent.VK_A);
//
//        selectField(CoursesPanel.NUMBER_FIELD_NAME);
//        type(KeyEvent.VK_1);
//
//        Thread.sleep(500);
//
//        assertTrue(button.isEnabled());
//    }

    private void selectField(String name) throws Exception {
        JTextField field = panel.getField(name);
        Point point = field.getLocationOnScreen();
        robot.mouseMove(point.x + field.getWidth() / 2, point.y + field.getHeight() / 2);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void type(int key) throws Exception {
        robot.keyPress(key);
        robot.keyRelease(key);
        Thread.sleep(500);
    }
}
