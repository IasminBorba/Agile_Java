package ui;

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
//        createInputDepFilters();
//        createInputNumberFilters();


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

//    private void createInputDepFilters() {
//        JTextField field = panel.getField(CoursesPanel.DEPARTMENT_FIELD_NAME);
//        UpcaseFilter upcaseFilter = new UpcaseFilter();
//        LimitFilter limitFilter = new LimitFilter(4);
//
//        upcaseFilter.setNextFilter(limitFilter);
//        ((AbstractDocument) field.getDocument()).setDocumentFilter(upcaseFilter);
//    }
//
//    private void createInputNumberFilters() {
//        JTextField fieldNumber = panel.getField(CoursesPanel.NUMBER_FIELD_NAME);
//        NumberFilter numberFilter = new NumberFilter();
//        LimitFilter limitFilter = new LimitFilter(3);
//
//        numberFilter.setNextFilter(limitFilter);
//        ((AbstractDocument) fieldNumber.getDocument()).setDocumentFilter(numberFilter);
//    }

    private void addCourse() {
//        Course course = new Course(panel.getText(1), panel.getText(2));
//        panel.addCourse(course);
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
                !isEmptyField(0) &&
                        !isEmptyField(1));
    }

    private boolean isEmptyField(int label) {
//        String value = panel.getText(label);
//        return value.trim().isEmpty();
        return true;
    }
}