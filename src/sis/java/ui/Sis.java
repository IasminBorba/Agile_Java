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

import static ui.CoursesPanel.COURSES_TABLE_NAME;

public class Sis {
    static final int WIDTH = 550;
    static final int HEIGHT = 600;
    private CoursesPanel panel;
    static final String COURSES_TITLE = "Course Listing";
    private final JFrame frame = new JFrame(COURSES_TITLE);

    public static void main(String[] args) {
        new Sis().show();
    }

    Sis() {
        initialize();
    }

    private void initialize() {
        createCoursePanel();
        createKeyListeners();
        createHelpPanel();
        panel.update();

        ImageIcon imageIcon = ImageUtil.create("images/courses.gif");
        frame.setIconImage(imageIcon.getImage());

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    JFrame getFrame() {
        return frame;
    }

    void close() {
        frame.dispose();
    }

    void createCoursePanel() {
        panel = new CoursesPanel();
        System.out.println("ADD_COURSE");
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ADD_COURSE2");
                addCourse();
            }
        });

        panel.removeCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeCourse();
            }
        });
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

    private void removeCourse() {
        List<Course> selectedCourses = panel.getSelectedCourses();
        if (!selectedCourses.isEmpty())
            for (Course course : selectedCourses)
                if (course != null) {
                    panel.removeCourse(course);
                    setRemoveButtonState();
                } else
                    JOptionPane.showMessageDialog(frame, "No course selected", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void addCourse() {
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                Course course;
                try {
                    course = new Course(panel.getDepartment(), panel.getNumber(), panel.getDate());
                } catch (Exception e) {
                    course = new Course(panel.getDepartment(), panel.getNumber());
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                panel.addCourse(course);
                setAddButtonState();
                return null;
            }

            @Override
            protected void done() {
                frame.setCursor(Cursor.getDefaultCursor());
            }
        };

        worker.execute();
    }

    void createKeyListeners() {
        JComboBox comboBoxDept = panel.getComboBoxDept();
        comboBoxDept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setButtonState();
            }
        });

        JComboBox comboBoxNum = panel.getComboBoxNum();
        comboBoxDept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setButtonState();
            }
        });

        JTable table = panel.getTable(COURSES_TABLE_NAME);
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

    private boolean isEmptyField(JComponent component) {
        if (component instanceof JTextField)
            return ((JTextField) component).getText().isEmpty();
        else if (component instanceof JComboBox<?>) {
            String field = ((String) ((JComboBox<Object>) component).getSelectedItem());
            return field == null;
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
}