package ui;

import javax.swing.*;

public class CoursesPanel extends JPanel {
    static final String LABEL_NAME = "coursesLabel";
    static final String NAME = "cousesPanel";
    static final String LABEL_TEXT = "Courses";
    public static void main(String[] args) {
        show(new CoursesPanel());
    }

    private static void show(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public CoursesPanel() {
        setName(NAME);
        createLayout();
    }

    private void createLayout() {
        JLabel label = new JLabel(LABEL_TEXT);
        label.setName(LABEL_NAME);
        add(label);
    }
}
