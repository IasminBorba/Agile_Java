package ui;

import studentinfo.Course;
import util.ImageUtil;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static ui.CoursesPanel.COURSES_TABLE_NAME;

public class Sis {
    static final int WIDTH = 350;
    static final int HEIGHT = 400;
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

        panel.updateCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCourse();
            }
        });
    }

    private void updateCourse() {
        List<Course> selectedCourses = panel.getSelectedCourses();
        if (selectedCourses.size() == 1)
            for (Course course : selectedCourses)
                if (course != null) {
                    Course newCourse;
                    try {
                        newCourse = new Course(panel.getDepartment(), panel.getNumber(), panel.getDate());
                    } catch (Exception e) {
                        newCourse = new Course(panel.getDepartment(), panel.getNumber());
                    }
                    panel.updateCourse(course, newCourse);
                    setButtonState();
                } else
                    JOptionPane.showMessageDialog(frame, "No course selected", "Error", JOptionPane.ERROR_MESSAGE);
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
        JTable table = panel.getTable(COURSES_TABLE_NAME);
        KeyListener listener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                setButtonState();
            }
        };
        panel.addFieldListener(FieldCatalog.DEPARTMENT_FIELD_NAME, listener);
        panel.addFieldListener(FieldCatalog.NUMBER_FIELD_NAME, listener);

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

        setButtonState();
    }

    void setButtonState() {
        setAddButtonState();
        setRemoveButtonState();
        setUpdateButtonState();
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, verifyFieldsAdd());
    }

    void setRemoveButtonState() {
        panel.setEnabled(CoursesPanel.REMOVE_BUTTON_NAME, verifyFields());
    }

    void setUpdateButtonState() {
        panel.setEnabled(CoursesPanel.UPDATE_BUTTON_NAME, panel.getSelectedCourses().size() == 1 && verifyFieldsUpdate());
    }

    private boolean verifyFieldsAdd() {
        List<String> fields = new ArrayList<>();
        fields.add(FieldCatalog.DEPARTMENT_FIELD_NAME);
        fields.add(FieldCatalog.NUMBER_FIELD_NAME);

        for (String fieldName : fields) {
            JTextField field = panel.getField(fieldName);
            if(!panel.getSelectedCourses().isEmpty())
                return false;

            if (isEmptyField(field))
                return false;
            if (!verifyFilter(field))
                return false;
        }
        return true;
    }

    private boolean verifyFieldsUpdate() {
        List<String> fields = new ArrayList<>();
        fields.add(FieldCatalog.DEPARTMENT_FIELD_NAME);
        fields.add(FieldCatalog.NUMBER_FIELD_NAME);

        for (String fieldName : fields) {
            JTextField field = panel.getField(fieldName);

            if (isEmptyField(field))
                return false;
            if (!verifyFilter(field))
                return false;
        }
        return true;
    }

    private boolean isEmptyField(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    private boolean verifyFields() {
        if (!panel.getSelectedCourses().isEmpty()) {
            for (String fieldName : panel.getFieldNames())
                if (!isEmptyField(panel.getField(fieldName)))
                    return false;
            return true;
        }
        return false;
    }

    private boolean verifyFilter(JTextField field) {
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
        return true;
    }
}