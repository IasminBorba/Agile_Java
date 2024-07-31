package ui;

import studentinfo.*;
import javax.swing.*;
import java.awt.event.*;

public class Sis {
    static final int WIDTH = 350;
    static final int HEIGHT = 500;
    private CoursesPanel panel;

    private final JFrame frame = new JFrame();
    public static void main(String[] args) {
        new Sis().show();
    }

    Sis() {
        initialize();
    }

    private void initialize() {
        createCoursePanel();

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
        Course course = new Course(panel.getText(CoursesPanel.DEPARTMENT_FIELD_NAME), panel.getText(CoursesPanel.NUMBER_FIELD_NAME));
        panel.addCourse(course);
    }
}
