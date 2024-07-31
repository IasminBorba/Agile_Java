package ui;

import javax.swing.*;

public class CoursesPanel extends JPanel {
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
        JLabel label = new JLabel(LABEL_TEXT);
        add(label);
    }
}
