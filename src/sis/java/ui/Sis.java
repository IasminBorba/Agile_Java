package ui;

import studentinfo.Course;
import util.ImageUtil;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Sis {
    static final int WIDTH = 550;
    static final int HEIGHT = 600;
    private final CoursesPanel panel= new CoursesPanel();
    static final String COURSES_TITLE = "Course Listing";
    private final JFrame frame = new JFrame(COURSES_TITLE);

    public static void main(String[] args) {
        new Sis().show();
    }

    Sis() {
        initialize();
        createHelpPanel();
    }

    public void show() {
        frame.setVisible(true);
    }

    void close() {
        frame.dispose();
    }

    private void initialize() {
        ImageIcon imageIcon = ImageUtil.create("images/courses.gif");
        frame.setIconImage(imageIcon.getImage());

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);

        createListeners();
    }

    void createHelpPanel() {
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("F1"), "openNewWindow");
        actionMap.put("openNewWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpWindow();
            }
        });
    }

    void createListeners() {
        createBoxsListeners();
        createButtonListeners();
        createTableListeners();
        addFilterUpdate();
    }

    protected void addFilterUpdate() {
        JTable table = panel.getTable();
        FieldCatalog catalog = panel.getCatalog();

        int column = 0;
        for (String fieldName : catalog.getFieldNames()) {
            FilteredCellEditor filteredCellEditor = new FilteredCellEditor(fieldName, catalog.get(fieldName));
            table.getColumnModel().getColumn(column).setCellEditor(filteredCellEditor);
            column++;
        }
    }

    void createBoxsListeners() {
        JComboBox comboBoxDept = panel.getComboBoxDept();
        comboBoxDept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setButtonState();
            }
        });

        JComboBox comboBoxNum = panel.getComboBoxNum();
        comboBoxNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setButtonState();
            }
        });
    }

    void createButtonListeners () {
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        panel.removeCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeCourse();
            }
        });
    }

    void createTableListeners() {
        JTable table = panel.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setButtonState();
            }
        });

        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = header.columnAtPoint(e.getPoint());
                panel.orderByColumn(columnIndex);
            }
        });

        table.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                Object newValue = table.getModel().getValueAt(row, column);
            }
        });

        setButtonState();
    }

    private void addCourse() {
        setWaitCursor(true);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                return performAddCourseTask();
            }

            @Override
            protected void done() {
                setWaitCursor(false);
            }
        };

        worker.execute();
    }

    private Void performAddCourseTask() throws Exception {
        Course course = new Course(panel.getDepartment(), panel.getNumber(), panel.getDate());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (verifyAddCourse(course))
            panel.addCourse(course);

        setAddButtonState();
        return null;
    }

    private void setWaitCursor(boolean wait) {
        frame.setCursor(wait ? Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) : Cursor.getDefaultCursor());
    }

    boolean verifyAddCourse(Course course) {
        JTable table = panel.getTable();
        CoursesTableModel coursesTableModel = (CoursesTableModel) table.getModel();
        for (Course c : coursesTableModel.getCourses())
            if (c.equals(course))
                return false;
        return true;
    }

    private void removeCourse() {
        List<Course> selectedCourses = panel.getSelectedCourses();
        if (!selectedCourses.isEmpty())
            for (Course course : selectedCourses) {
                if (verifyRemoveCourse(course))
                    panel.removeCourse(course);
                setRemoveButtonState();
            }
    }

    boolean verifyRemoveCourse(Course course) {
        JTable table = panel.getTable();
        CoursesTableModel coursesTableModel = (CoursesTableModel) table.getModel();
        for (Course c : coursesTableModel.getCourses())
            if (c.equals(course))
                return true;
        return false;
    }

    void setButtonState() {
        setAddButtonState();
        setRemoveButtonState();
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, verifyFieldsAdd());
    }

    void setRemoveButtonState() {
        panel.setEnabled(CoursesPanel.REMOVE_BUTTON_NAME, verifyFields());
    }

    private boolean verifyFieldsAdd() {
        List<String> fields = new ArrayList<>();
        fields.add(FieldCatalog.DEPARTMENT_FIELD_NAME);
        fields.add(FieldCatalog.NUMBER_FIELD_NAME);

        for (String fieldName : fields) {
            JComponent field = panel.getField(fieldName);

            if (!panel.getSelectedCourses().isEmpty())
                return false;
            if (isEmptyField(field))
                return false;
            if (!verifyFilter(field))
                return false;
        }
        return true;
    }

    private boolean verifyFields() {
        List<String> fields = new ArrayList<>();
        fields.add(FieldCatalog.DEPARTMENT_FIELD_NAME);
        fields.add(FieldCatalog.NUMBER_FIELD_NAME);

        for (String fieldName : fields) {
            JComponent field = panel.getField(fieldName);

            if (!isEmptyField(field))
                return false;
        }
        return !panel.getSelectedCourses().isEmpty();
    }

    private boolean isEmptyField(JComponent component) {
        if (component instanceof JTextField)
            return ((JTextField) component).getText().isEmpty();
        else if (component instanceof JComboBox<?>) {
            String field = ((String) ((JComboBox<Object>) component).getSelectedItem());
            return field == null;
        }
        return true;
    }

    private boolean verifyFilter(JComponent component) {
        if (component instanceof JTextField field) {
            AbstractDocument document = (AbstractDocument) field.getDocument();
            ChainableFilter currentFilter = (ChainableFilter) document.getDocumentFilter();

            while (currentFilter != null) {
                String textField = field.getText();
                if (!currentFilter.verify(textField))
                    return false;

                if (currentFilter instanceof ChainableFilter chainableFilter)
                    currentFilter = (ChainableFilter) chainableFilter.getNextFilter();
                else
                    break;
            }
        }
        return true;
    }

    JFrame getFrame() {
        return frame;
    }
}