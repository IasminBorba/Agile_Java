package ui;

import junit.framework.TestCase;
import studentinfo.*;
import util.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ui.CoursesPanel.COURSES_TABLE_NAME;

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

    public void testAddCourse() throws InterruptedException {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, true);
        panel.setText(FieldCatalog.DEPARTMENT_FIELD_NAME, "MATH");
        panel.setText(FieldCatalog.NUMBER_FIELD_NAME, "300");

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);

        button.doClick();
        Thread.sleep(1000);

        Course course = panel.getCourse(0);
        assertEquals("MATH", course.getDepartment());
        assertEquals("300", course.getNumber());
    }

    public void testRemoveCourse() {
        JButton button = panel.getButton(CoursesPanel.REMOVE_BUTTON_NAME);
        assertFalse(button.isEnabled());

        JTable table = panel.getTable(COURSES_TABLE_NAME);
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course1 = new Course("CourseA", "101");
        panel.addCourse(course1);

        Course course2 = new Course("CourseB", "202");
        panel.addCourse(course2);

        Course course3 = new Course("CourseC", "303");
        panel.addCourse(course3);

        Course course4 = new Course("CourseD", "404");
        panel.addCourse(course4);

        assertTrue(model.getRowCount() == 4);

        panel.setSelected(course1, course3, course4);
        sis.setRemoveButtonState();
        assertTrue(button.isEnabled());

        button.doClick();

        assertTrue(model.getRowCount() == 1);
        assertSame(course2, model.get(0));
    }

    public void testUpdateCourse() {
        JTable table = panel.getTable(COURSES_TABLE_NAME);
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course1 = new Course("CourseA", "101");
        panel.addCourse(course1);

        Course course2 = new Course("CourseB", "202");
        panel.addCourse(course2);

        panel.setCellSelected(0, 0);
        panel.updateCell("NEWC");
        assertEquals("NEWC", course1.getDepartment());

        panel.setCellSelected(1, 1);
        panel.updateCell("999");
        assertEquals("999", course2.getNumber());
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

    public void testSetRemoveButtonState() throws Exception {
        JButton button = panel.getButton(CoursesPanel.REMOVE_BUTTON_NAME);
        assertFalse(button.isEnabled());

        JTable table = panel.getTable(COURSES_TABLE_NAME);
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course1 = new Course("ENGL", "101");
        panel.addCourse(course1);
        assertSame(course1, model.get(0));

        Course course2 = new Course("a", "123");
        panel.addCourse(course2);
        assertSame(course2, model.get(1));

        assertTrue(model.getRowCount() == 2);

        panel.setSelected(course1);
        sis.setRemoveButtonState();
        assertTrue(button.isEnabled());

        button.doClick();

        assertTrue(model.getRowCount() == 1);
        assertSame(course2, model.get(0));

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

    public void testOrderByColumn() throws Exception {
        JTable table = panel.getTable(COURSES_TABLE_NAME);
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course1 = new Course("CourseA", "777");
        panel.addCourse(course1);

        Course course2 = new Course("CourseC", "555");
        panel.addCourse(course2);

        Course course3 = new Course("CourseB", "111");
        panel.addCourse(course3);

        assertTrue(model.getRowCount() == 3);
        String dept = FieldCatalog.DEPARTMENT_FIELD_NAME;
        String num = FieldCatalog.NUMBER_FIELD_NAME;

        panel.orderByColumn(model.getColumnIndex(dept));
        Course[] courses1 = {course1, course3, course2};
        assertOrder(model.getCourses(), courses1);


        panel.orderByColumn(model.getColumnIndex(num));
        Course[] courses2 = {course3, course2, course1};
        assertOrder(model.getCourses(), courses2);


        Course course4 = new Course("CourseA", "333");
        panel.addCourse(course4);

        panel.orderByColumn(model.getColumnIndex(num));
        Course[] courses3 = {course3, course4, course2, course1};
        assertOrder(model.getCourses(), courses3);

        panel.orderByColumn(model.getColumnIndex(dept));
        Course[] courses4 = {course4, course1, course3, course2};
        assertOrder(model.getCourses(), courses4);
    }

    private void assertOrder(List<Course> tableListCourses, Course[] courseList) {
        assertEquals(courseList.length, tableListCourses.size());
        int indexMAX = courseList.length - 1;
        List<Course> courses = Arrays.stream(courseList).toList();

        while (indexMAX >= 0) {
            assertSame(courses.get(indexMAX), tableListCourses.get(indexMAX));
            indexMAX--;
        }
    }
}
