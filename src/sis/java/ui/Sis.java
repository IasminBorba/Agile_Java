package ui;

import studentinfo.Course;
import util.ImageUtil;
import javax.swing.*;
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
        Course course = new Course(panel.getText(FieldCatalog.DEPARTMENT_FIELD_NAME), panel.getText(FieldCatalog.NUMBER_FIELD_NAME));
        panel.addCourse(course);
    }

    void createKeyListeners() {
        KeyListener listener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                setAddButtonState();
            }
        };
        panel.addFieldListener(FieldCatalog.DEPARTMENT_FIELD_NAME,
                listener);
        panel.addFieldListener(FieldCatalog.NUMBER_FIELD_NAME, listener);
        setAddButtonState();
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME,
                !isEmptyField(FieldCatalog.DEPARTMENT_FIELD_NAME) &&
                        !isEmptyField(FieldCatalog.NUMBER_FIELD_NAME));
    }

    private boolean isEmptyField(String field) {
        String value = panel.getText(field);
        return value.trim().isEmpty();
    }
}