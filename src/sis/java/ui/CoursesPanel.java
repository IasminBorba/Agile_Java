package ui;

import studentinfo.Course;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static java.awt.GridBagConstraints.*;
import static junit.framework.Assert.fail;

public class CoursesPanel extends JPanel {
    static final String NAME = "coursesPanel";
    static final String COURSES_LABEL_TEXT = "Courses";
    static final String COURSES_TABLE_NAME = "coursesTable";

    private final Map<String, JTextField> fieldsMap = new HashMap<>();
    public StatusBar statusBar;

    private JButton addButton;
    static final String ADD_BUTTON_TEXT = "Add";
    static final String ADD_BUTTON_NAME = "addButton";
    static final char ADD_BUTTON_MNEMONIC = 'A';

    private JButton removeButton;
    static final String REMOVE_BUTTON_TEXT = "Remove";
    static final String REMOVE_BUTTON_NAME = "removeButton";

    private final CoursesTableModel coursesTableModel = new CoursesTableModel();
    JTable coursesTable = createCoursesTable();

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
        JScrollPane coursesScroll = new JScrollPane(coursesTable);
        coursesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());

        final int pad = 6;
        Border emptyBorder = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border titleBorder = BorderFactory.createTitledBorder(bevelBorder, COURSES_LABEL_TEXT);
        setBorder(BorderFactory.createCompoundBorder(emptyBorder, titleBorder));

        add(coursesScroll, BorderLayout.CENTER);
        add(createSouthPanel(), BorderLayout.SOUTH);
    }

    private JTable createCoursesTable() {
        JTable table = new JTable(coursesTableModel);
        table.setName(COURSES_TABLE_NAME);
        table.setShowGrid(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    JPanel createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 6)));

        panel.add(createBottomPanel());

        panel.add(Box.createRigidArea(new Dimension(0, 6)));
        panel.add(createInputPanel());

        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return panel;
    }

    JPanel createBottomPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        addButton.setMnemonic(ADD_BUTTON_MNEMONIC);
        buttonPanel.add(addButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        removeButton = createButton(REMOVE_BUTTON_NAME, REMOVE_BUTTON_TEXT);
        buttonPanel.add(removeButton);

        return buttonPanel;
    }

    JPanel createInputPanel() {
        statusBar = new StatusBar();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(statusBar, BorderLayout.SOUTH);
        panel.add(createFieldsPanel(), BorderLayout.CENTER);
        return panel;
    }

    JPanel createFieldsPanel() {
        GridBagLayout layout = new GridBagLayout();
        FieldCatalog catalog = new FieldCatalog();

        JPanel panel = new JPanel(layout);
        int i = 0;

        for (String fieldName : getFieldNames()) {
            Field fieldSpec = catalog.get(fieldName);
            JTextField textField = TextFieldFactory.create(fieldSpec);
            statusBar.setInfo(textField, fieldSpec.getInfo());
            fieldsMap.put(fieldSpec.getName(), textField);
            addField(panel, layout, i++, createLabel(fieldSpec), textField);
        }
        return panel;
    }

    private String[] getFieldNames() {
        return new String[]{
                FieldCatalog.DEPARTMENT_FIELD_NAME,
                FieldCatalog.NUMBER_FIELD_NAME,
                FieldCatalog.EFFECTIVE_DATE_FIELD_NAME,
        };
    }

    private void addField(JPanel panel, GridBagLayout layout, int row, JLabel label, JTextField field) {
        Insets insets = new Insets(3, 3, 3, 3);
        layout.setConstraints(label, new GridBagConstraints(
                0, row,
                1, 1,
                40, 1,
                LINE_END,
                NONE,
                insets,
                0, 0
        ));
        layout.setConstraints(field, new GridBagConstraints(
                1, row,
                2, 1, 60, 1,
                CENTER, HORIZONTAL,
                insets,
                0, 0
        ));

        panel.add(label);
        panel.add(field);
    }

    public Course getSelectedCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = coursesTable.convertRowIndexToModel(selectedRow);
            return coursesTableModel.get(modelRow);
        }
        return null;
    }


    void removeCourse(Course... courses) {
        for (Course course: courses)
            if (verifyCourse(course))
                coursesTableModel.remove(course);
    }

    void addCourse(Course course) {
        if (verifyCourse(course)) {
            coursesTableModel.add(course);
            clearFields();
        }
    }

    void clearFields() {
        for (String fieldName : fieldsMap.keySet()) {
            fieldsMap.get(fieldName).setText("");
        }
    }

    boolean verifyCourse(Course course) {
        for (Course c : coursesTableModel.getCourses()) {
            if (c.equals(course)) {
                return false;
            }
        }
        return true;
    }

    private JLabel createLabel(Field field) {
        JLabel label = new JLabel(field.getText());
        label.setName(field.getName());
        return label;
    }

    private JButton createButton(String name, String text) {
        JButton button = new JButton(text);
        button.setName(name);
        return button;
    }

    void addCourseAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    void addRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    Course getCourse(int index) {
        return coursesTableModel.get(index);
    }

    JLabel getLabel(String name) {
        return (JLabel) Util.getComponent(this, name);
    }

    JTable getTable(String name) {
        return (JTable) Util.getComponent(this, name);
    }

    JButton getButton(String name) {
        return (JButton) Util.getComponent(this, name);
    }

    JTextField getField(String name) {
        return fieldsMap.get(name);
    }

    String getTitle() {
        Border border = this.getBorder();
        if (border instanceof CompoundBorder compoundBorder) {
            Border innerBorder = compoundBorder.getInsideBorder();
            if (innerBorder instanceof TitledBorder titledBorder)
                return titledBorder.getTitle();
            else
                fail("Inner border is not an instance of TitledBorder");
        }
        return "Border is not an instance of CompoundBorder";
    }

    void setText(String textFieldName, String text) {
        getField(textFieldName).setText(text);
    }

    String getText(String textFIeldName) {
        return getField(textFIeldName).getText();
    }

    LocalDate getDate() {
        return LocalDate.parse(getField(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME).getText(), FieldCatalog.DEFAULT_DATE_FORMAT);
    }

    String getDepartment() {
        return getField(FieldCatalog.DEPARTMENT_FIELD_NAME).getText();
    }

    String getNumber() {
        return getField(FieldCatalog.NUMBER_FIELD_NAME).getText();
    }

    void setEnabled(String name, boolean state) {
        getButton(name).setEnabled(state);
    }

    void addFieldListener(String name, KeyListener listener) {
        getField(name).addKeyListener(listener);
    }

    int getCourseCount() {
        return coursesTableModel.getRowCount();
    }

    Map<String, JTextField> getFields() {
        return fieldsMap;
    }
}
