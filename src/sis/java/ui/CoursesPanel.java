package ui;

import studentinfo.Course;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import static java.awt.GridBagConstraints.*;
import static junit.framework.Assert.fail;

public class CoursesPanel extends JPanel {
    static final String NAME = "coursesPanel";
    static final String COURSES_LABEL_TEXT = "Courses - Help(F1)";
    static final String COURSES_TABLE_NAME = "coursesTable";

    private final Map<String, JComponent> fieldsMap = new HashMap<>();
    public StatusBar statusBar;
    private JComboBox boxDept;
    private JComboBox boxNum;

    private JButton addButton;
    static final String ADD_BUTTON_TEXT = "Add";
    static final String ADD_BUTTON_NAME = "addButton";
    static final char ADD_BUTTON_MNEMONIC = 'A';

    private JButton removeButton;
    static final String REMOVE_BUTTON_TEXT = "Remove";
    static final String REMOVE_BUTTON_NAME = "removeButton";
    static final char REMOVE_BUTTON_MNEMONIC = 'D';

    private final CoursesTableModel coursesTableModel = new CoursesTableModel();
    JTable coursesTable = createCoursesTable();
    FieldCatalog catalog = new FieldCatalog();

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
        createBoxs();
        setName(NAME);
        createLayout();
    }

    private void createBoxs() {
        Object[] stringDpt = catalog.get(FieldCatalog.DEPARTMENT_FIELD_NAME).getComboBoxOptions().toArray();
        boxDept = new JComboBox<>(stringDpt);

        Object[] stringNum = catalog.get(FieldCatalog.NUMBER_FIELD_NAME).getComboBoxOptions().toArray();
        boxNum = new JComboBox<>(stringNum);
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
        TableRowSorter<CoursesTableModel> sorter = new TableRowSorter<>(coursesTableModel);

        table.setName(COURSES_TABLE_NAME);
        table.setShowGrid(false);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setRowSorter(sorter);
        table.getAutoResizeMode();
        table.setCellSelectionEnabled(true);
        return table;
    }

    protected void update() {
        int column = 0;
        for (String fieldName : getFieldNames()) {
            FilteredCellEditor filteredCellEditor = new FilteredCellEditor(fieldName, catalog.get(fieldName));
            coursesTable.getColumnModel().getColumn(column).setCellEditor(filteredCellEditor);
            column++;
        }
    }

    public static void adjustColumnWidthsAverage(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        TableCellRenderer renderer = table.getTableHeader().getDefaultRenderer();

        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            int totalWidth = 0;
            int rowCount = table.getRowCount();

            for (int row = 0; row < rowCount; row++) {
                Object value = table.getValueAt(row, column);
                Component cellComponent = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                totalWidth += cellComponent.getPreferredSize().width;
            }

            int averageWidth = (rowCount > 0) ? totalWidth / rowCount : 0;
            tableColumn.setPreferredWidth(averageWidth + 10);
        }
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
        removeButton.setMnemonic(REMOVE_BUTTON_MNEMONIC);
        buttonPanel.add(removeButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

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

        JPanel panel = new JPanel(layout);
        int i = 0;

        for (String fieldName : getFieldNames()) {
            Field fieldSpec = catalog.get(fieldName);

            if (fieldSpec.isComboBox()) {
                JComboBox comboBox;
                if (Objects.equals(fieldName, FieldCatalog.DEPARTMENT_FIELD_NAME))
                    comboBox = boxDept;
                else
                    comboBox = boxNum;
                comboBox.setName(fieldSpec.getName());
                statusBar.setInfo(comboBox, fieldSpec.getInfo());
                fieldsMap.put(fieldSpec.getName(), comboBox);
                addField(panel, layout, i++, createLabel(fieldSpec), comboBox);
            } else {
                JTextField textField = TextFieldFactory.create(fieldSpec);
                statusBar.setInfo(textField, fieldSpec.getInfo());
                fieldsMap.put(fieldSpec.getName(), textField);
                addField(panel, layout, i++, createLabel(fieldSpec), textField);
            }
        }
        return panel;
    }

    protected String[] getFieldNames() {
        return new String[]{
                FieldCatalog.DEPARTMENT_FIELD_NAME,
                FieldCatalog.NUMBER_FIELD_NAME,
                FieldCatalog.EFFECTIVE_DATE_FIELD_NAME,
        };
    }

    private void addField(JPanel panel, GridBagLayout layout, int row, JLabel label, JComponent field) {
        Insets insets = new Insets(3, 3, 3, 3);
        layout.setConstraints(label, new GridBagConstraints(
                0, row,
                1, 1,
                40, 1,
                LINE_END, NONE,
                insets,
                0, 0
        ));
        layout.setConstraints(field, new GridBagConstraints(
                1, row,
                2, 1,
                60, 1,
                CENTER, HORIZONTAL,
                insets,
                0, 0
        ));

        panel.add(label);
        panel.add(field);
    }

    public List<Course> getSelectedCourses() {
        List<Course> courses = new ArrayList<>();
        int[] selectedRows = coursesTable.getSelectedRows();

        for (int index : selectedRows)
            courses.add(coursesTableModel.get(index));

        return courses;
    }

    public void orderByColumn(int columnIndex) {
        coursesTable.getRowSorter().toggleSortOrder(columnIndex);
        coursesTableModel.sort(columnIndex);
    }

    void removeCourse(Course... courses) {
        for (Course course : courses)
            if (verifyRemoveCourse(course)) {
                int index = coursesTableModel.getIndexCourse(course);
                coursesTableModel.remove(course);
            }
        coursesTable.clearSelection();
    }

    void addCourse(Course course) {
        if (verifyAddCourse(course)) {
            coursesTableModel.add(course);
            clearFields();
        }
        adjustColumnWidthsAverage(coursesTable);
    }

    void clearFields() {
        for (String fieldName : fieldsMap.keySet()) {
            JComponent component = fieldsMap.get(fieldName);
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JComboBox) {
                ((JComboBox<?>) component).setSelectedIndex(-1);
            }
        }
    }

    boolean verifyAddCourse(Course course) {
        for (Course c : coursesTableModel.getCourses())
            if (c.equals(course))
                return false;
        return true;
    }

    boolean verifyRemoveCourse(Course course) {
        for (Course c : coursesTableModel.getCourses())
            if (c.equals(course))
                return true;
        return false;
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

    JComboBox getComboBoxDept() {
        return boxDept;
    }

    JComboBox getComboBoxNum() {
        return boxNum;
    }

    void addCourseAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    void removeCourseAddListener(ActionListener listener) {
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

    JComponent getField(String name) {
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
        JComponent component = fieldsMap.get(textFieldName);
        if (component instanceof JTextField) {
            ((JTextField) component).setText(text);
        } else if (component instanceof JComboBox) {
            ((Field) component).addComboBoxOptions(text);
        }

        getField(textFieldName).setToolTipText(text);
    }

    void setSelected(Course... courses) {
        List<Integer> indexs = new ArrayList<>();

        for (Course courseTable : coursesTableModel.getCourses())
            for (Course courseList : courses)
                if (courseTable.equals(courseList))
                    indexs.add(coursesTableModel.getIndexCourse(courseList));

        if (!indexs.isEmpty()) {
            for (int index : indexs) {
                coursesTable.addRowSelectionInterval(index, index);
            }
        }
    }

    void setCellSelected(int row, int column) {
        coursesTable.editCellAt(row, column);
    }

    void updateCell(String newText) {
        if (coursesTable.getCellEditor() != null) {
            JTextField editorComponent = (JTextField) coursesTable.getEditorComponent();
            editorComponent.setText(newText);

            if (coursesTable.isEditing()) {
                coursesTable.getCellEditor().stopCellEditing();
            }
        }
    }

    String getText(String textFieldName) {
        JComponent component = fieldsMap.get(textFieldName);
        if (component instanceof JTextField) {
            return ((JTextField) component).getText();
        } else if (component instanceof JComboBox) {
            return (String) ((JComboBox<?>) component).getSelectedItem();
        }
        return "";
    }

    LocalDate getDate() {
        return LocalDate.parse(getText(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME), FieldCatalog.DEFAULT_DATE_FORMAT);
    }

    String getDepartment() {
        return getText(FieldCatalog.DEPARTMENT_FIELD_NAME);
    }

    String getNumber() {
        return getText(FieldCatalog.NUMBER_FIELD_NAME);
    }

    void setEnabled(String name, boolean state) {
        Color color = null;
        if (state) {
            if (Objects.equals(name, ADD_BUTTON_NAME))
                color = Color.decode("#82A966");
            if (Objects.equals(name, REMOVE_BUTTON_NAME))
                color = Color.decode("#E03A36");
        }
        getButton(name).setEnabled(state);
        getButton(name).setBackground(color);
    }

    void addFieldListener(String name, KeyListener listener) {
        getField(name).addKeyListener(listener);
    }

    Map<String, JComponent> getFields() {
        return fieldsMap;
    }
}
