package ui;

import javax.swing.*;

public class Sis {
    static final int WIDTH = 350;
    static final int HEIGHT = 500;

    private final JFrame frame = new JFrame();
    public static void main(String[] args) {
        new Sis().show();
    }

    Sis() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CoursesPanel());
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
}
