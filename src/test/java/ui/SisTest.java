package ui;

import junit.framework.TestCase;
import studentinfo.*;
import util.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SisTest extends TestCase {
    private Sis sis;
    private JFrame frame;
    private CoursesPanel panel;
    private Robot robot;
    private FieldCatalog catalog;
    private JTable table;
    private CoursesTableModel coursesTableModel;

    protected void setUp() throws AWTException {
        sis = new Sis();
        frame = sis.getFrame();
        panel = (CoursesPanel) Util.getComponent(frame, CoursesPanel.NAME);
        robot = new Robot();
        catalog = new FieldCatalog();
        table = panel.getTable();
        coursesTableModel = panel.getCoursesTableModel();
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
        JTextField field = (JTextField) panel.getField(fieldName);
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
        setText(FieldCatalog.DEPARTMENT_FIELD_NAME, "MATH");
        setText(FieldCatalog.NUMBER_FIELD_NAME, "300");

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);

        button.doClick();
        Thread.sleep(1000);

        Course course = coursesTableModel.get(0);
        assertEquals("MATH", course.getDepartment());
        assertEquals("300", course.getNumber());
    }

    public void testRemoveCourse() {
        JButton button = panel.getButton(CoursesPanel.REMOVE_BUTTON_NAME);
        assertFalse(button.isEnabled());

        JTable table = panel.getTable();
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course1 = new Course("CourseA", "101", "");
        sis.addCourse(course1);

        Course course2 = new Course("CourseB", "202", "");
        sis.addCourse(course2);

        Course course3 = new Course("CourseC", "303", "");
        sis.addCourse(course3);

        Course course4 = new Course("CourseD", "404", "");
        sis.addCourse(course4);

        assertTrue(model.getRowCount() == 4);

        setSelected(course1, course3, course4);
        sis.setRemoveButtonState();
        assertTrue(button.isEnabled());

        button.doClick();

        assertTrue(model.getRowCount() == 1);
        assertSame(course2, model.get(0));
    }

    private void setSelected(Course... courses) {
        List<Integer> indexs = new ArrayList<>();

        for (Course courseTable : coursesTableModel.getCourses())
            for (Course courseList : courses)
                if (courseTable.equals(courseList))
                    indexs.add(coursesTableModel.getIndexCourse(courseList));

        if (!indexs.isEmpty()) {
            for (int index : indexs) {
                table.addRowSelectionInterval(index, index);
            }
        }
    }

    public void testUpdateCourse() {
        JTable table = panel.getTable();
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course1 = new Course("CourseA", "101", "");
        sis.addCourse(course1);

        Course course2 = new Course("CourseB", "202", "");
        sis.addCourse(course2);

        setCellSelected(0, 0);
        updateCell("NEWC");
        assertEquals("NEWC", course1.getDepartment());

        setCellSelected(1, 1);
        updateCell("999");
        assertEquals("999", course2.getNumber());
    }

    void setCellSelected(int row, int column) {
        table.editCellAt(row, column);
    }

    void updateCell(String newText) {
        if (table.getCellEditor() != null) {
            JTextField editorComponent = (JTextField) table.getEditorComponent();
            editorComponent.setText(newText);

            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }
        }
    }

    public void testSetAddButtonState() throws Exception {
        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());

        setText(FieldCatalog.DEPARTMENT_FIELD_NAME, "a");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());

        setText(FieldCatalog.NUMBER_FIELD_NAME, "1");
        sis.setAddButtonState();
        assertTrue(button.isEnabled());

        setText(FieldCatalog.DEPARTMENT_FIELD_NAME, "");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());

        setText(FieldCatalog.DEPARTMENT_FIELD_NAME, "a");
        setText(FieldCatalog.NUMBER_FIELD_NAME, "123");
        sis.setAddButtonState();
        assertTrue(button.isEnabled());
    }

    public void testSetRemoveButtonState() throws Exception {
        JButton button = panel.getButton(CoursesPanel.REMOVE_BUTTON_NAME);
        assertFalse(button.isEnabled());

        JTable table = panel.getTable();
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course1 = new Course("ENGL", "101", "");
        sis.addCourse(course1);
        assertSame(course1, model.get(0));

        Course course2 = new Course("a", "123", "");
        sis.addCourse(course2);
        assertSame(course2, model.get(1));

        assertTrue(model.getRowCount() == 2);

        setSelected(course1);
        sis.setRemoveButtonState();
        assertTrue(button.isEnabled());

        button.doClick();

        assertTrue(model.getRowCount() == 1);
        assertSame(course2, model.get(0));

    }

    public void testOrderByColumn() throws Exception {
        JTable table = panel.getTable();
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course1 = new Course("CourseA", "777", "");
        sis.addCourse(course1);

        Course course2 = new Course("CourseC", "555", "");
        sis.addCourse(course2);

        Course course3 = new Course("CourseB", "111", "");
        sis.addCourse(course3);

        assertTrue(model.getRowCount() == 3);
        String dept = FieldCatalog.DEPARTMENT_FIELD_NAME;
        String num = FieldCatalog.NUMBER_FIELD_NAME;

        panel.orderByColumn(model.getColumnIndex(dept));
        Course[] courses1 = {course1, course3, course2};
        assertOrder(model.getCourses(), courses1);


        panel.orderByColumn(model.getColumnIndex(num));
        Course[] courses2 = {course3, course2, course1};
        assertOrder(model.getCourses(), courses2);


        Course course4 = new Course("CourseA", "333", "");
        sis.addCourse(course4);

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

    public void testComboBox() throws Exception {
        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());

        String[] deptStrings = {"ABC", "CBA", "DEF", "FED"};
        String[] numStrings = {"123", "321", "456", "654"};

        setListStringsBox(deptStrings, numStrings);
        selectedIndexs(2, 1);

        assertEquals("DEF", (panel.getComboBoxDept()).getSelectedItem());
        assertEquals("321", (panel.getComboBoxNum()).getSelectedItem());

        sis.setAddButtonState();
        assertTrue(button.isEnabled());

        button.doClick();
        Thread.sleep(1000);

        Course course = coursesTableModel.get(0);
        assertEquals("DEF", course.getDepartment());
        assertEquals("321", course.getNumber());
    }

    void setListStringsBox(String[] deptStrings, String[] numStrings) {
        JComboBox comboBoxDept = panel.getComboBoxDept();
        comboBoxDept.removeAllItems();
        for (String str : deptStrings)
            comboBoxDept.addItem(str);

        JComboBox comboBoxNum = panel.getComboBoxNum();
        comboBoxNum.removeAllItems();
        for (String str : numStrings)
            comboBoxNum.addItem(str);
    }

    void selectedIndexs(int indexDept, int indexNum) {
        JComboBox comboBoxDept = panel.getComboBoxDept();
        comboBoxDept.setSelectedIndex(indexDept);

        JComboBox comboBoxNum = panel.getComboBoxNum();
        comboBoxNum.setSelectedIndex(indexNum);
    }

    public void testAddCourseDuplicate() {
        JTable table = panel.getTable();
        CoursesTableModel model = (CoursesTableModel) table.getModel();

        Course course = new Course("ENGL", "101", "");
        sis.addCourse(course);
        assertSame(course, model.get(0));

        Course course2 = new Course("ENGL", "101", "");
        if (sis.verifyAddCourse(course2))
            sis.addCourse(course2);

        assertFalse((model.getCourses().size()) == 2);
    }

    void setText(String textFieldName, String text) {
        JComponent component = panel.getField(textFieldName);

        if (component instanceof JTextField)
            ((JTextField) component).setText(text);
        else if (component instanceof JComboBox)
            ((Field) component).addComboBoxOptions(text);
    }
}