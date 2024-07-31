package ui;

import javax.swing.*;

public class CoursesPanel extends JPanel {
    static final String NAME = "cousesPanel";
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
        createLabel(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);

        createList(COURSES_LIST_NAME);
        createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);

        int columns = 20;
        createLabel(DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT);
        createField(DEPARTMENT_FIELD_NAME, columns);

        createLabel(NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT);
        createField(NUMBER_FIELD_NAME, columns);
    }

    private void createLabel(String name, String text) {
        JLabel label = new JLabel(text);
        label.setName(name);
        add(label);
    }

    private void createList(String name) {
        JList list = new JList();
        list.setName(name);
        add(list);
    }

    private void createButton(String name, String text) {
        JButton button = new JButton(text);
        button.setName(name);
        add(button);
    }

    private void createField(String name, int columns) {
        JTextField field = new JTextField(columns);
        field.setName(name);
        add(field);
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
}
