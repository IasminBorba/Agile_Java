package ui;

import studentinfo.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CoursesPanel extends JPanel {
    static final String NAME = "coursesPanel";
    static final String COURSES_LABEL_TEXT = "Courses";
    static final String COURSES_LABEL_NAME = "coursesLabel";
    static final String COURSES_LIST_NAME = "coursesList";
    static final String ADD_BUTTON_TEXT = "Add";
    static final String ADD_BUTTON_NAME = "addButton";
    static final String DEPARTMENT_FIELD_NAME = "deptField";
    static final String NUMBER_FIELD_NAME = "numberField";
    static final String DEPARTMENT_LABEL_NAME = "deptLabel";
    static final String NUMBER_LABEL_NAME = "numberLabel";
    static final String DEPARTMENT_LABEL_TEXT = "Department";
    static final String NUMBER_LABEL_TEXT = "Number";
    private JButton addButton;
    private final DefaultListModel coursesModel = new DefaultListModel();

    public static void main(String[] args) {
        show(new CoursesPanel());
    }

    private static void show(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public CoursesPanel() {
        setName(NAME);
        createLayout();
    }

    private void createLayout() {
        JLabel coursesLabel = createLabel(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
        JList coursesList = createList(COURSES_LIST_NAME, coursesModel);
        setLayout(new BorderLayout());
        add(coursesLabel, BorderLayout.NORTH);
        add(coursesList, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    JPanel createBottomPanel() {
        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(addButton, BorderLayout.NORTH);
        panel.add(createFieldsPanel(), BorderLayout.SOUTH);
        return panel;
    }

    JPanel createFieldsPanel() {
        int columns = 20;
        JLabel departmentLabel = createLabel(DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT);
        JTextField departmentField = createField(DEPARTMENT_FIELD_NAME, columns);
        JLabel numberLabel = createLabel(NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT);
        JTextField numberField = createField(NUMBER_FIELD_NAME, columns);

        int rows = 2;
        int cols = 2;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols));
        panel.add(departmentLabel);
        panel.add(departmentField);
        panel.add(numberLabel);
        panel.add(numberField);
        return panel;
    }

    void addCourse(Course course) {
        coursesModel.addElement(new CourseDisplayAdapter(course));
    }

    private JLabel createLabel(String name, String text) {
        JLabel label = new JLabel(text);
        label.setName(name);
        return label;
    }

    private JList createList(String name, ListModel model) {
        JList list = new JList(model);
        list.setName(name);
        return list;
    }

    private JButton createButton(String name, String text) {
        JButton button = new JButton(text);
        button.setName(name);
        return button;
    }

    private JTextField createField(String name, int columns) {
        JTextField field = new JTextField(columns);
        field.setName(name);
        return field;
    }

    void addCourseAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    Course getCourse(int index) {
        Course adapter = (CourseDisplayAdapter)coursesModel.getElementAt(index);
        return adapter;
    }

    JLabel getLabel(String name) {
        return (JLabel) Util.getComponent(this, name);
    }

    JList getList(String name) {
        return (JList) Util.getComponent(this, name);
    }

    JButton getButton(String name) {
        return (JButton) Util.getComponent(this, name);
    }

    JTextField getField(String name) {
        return (JTextField) Util.getComponent(this, name);
    }

    void setText(String textFieldName, String text) {
        getField(textFieldName).setText(text);
    }

    String getText(String textFIeldName) {
        return getField(textFIeldName).getText();
    }
}
