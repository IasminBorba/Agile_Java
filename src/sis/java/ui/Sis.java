package ui;

import studentinfo.Course;
import util.ImageUtil;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.*;

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
    }

    private void addCourse() {
        Course course;
        try {
            course = new Course(panel.getDepartment(), panel.getNumber(), panel.getDate());
        } catch (Exception e) {
            course = new Course(panel.getDepartment(), panel.getNumber());
        }
        panel.addCourse(course);
    }

    void createKeyListeners() {
        KeyListener listener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                setAddButtonState();
            }
        };
        panel.addFieldListener(FieldCatalog.DEPARTMENT_FIELD_NAME, listener);
        panel.addFieldListener(FieldCatalog.NUMBER_FIELD_NAME, listener);
        setAddButtonState();
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME,
                verifyFilterField(FieldCatalog.DEPARTMENT_FIELD_NAME, FieldCatalog.NUMBER_FIELD_NAME));
    }

    private boolean verifyFilterField(String... fields) {
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