package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HelpWindow extends JFrame {
    static final int WIDTH = 480;
    static final int HEIGHT = 300;
    static final String HELP_TITLE = "Help";
    private final JFrame frame = new JFrame(HELP_TITLE);

    HelpWindow() {
        initialize();
    }

    private void initialize() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(createLayout());
        frame.setVisible(true);
    }

    private JPanel createLayout() {
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(createTextPanel());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JScrollPane createTextPanel() {
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText("<html>"
                + "<h1 style='font-family:sans-serif; color:#2A2A2A;'>Adding a Course:</h1>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "&emsp;To add a course, you need to fill in the <b>\"Department\"</b></p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "and <b>\"Number\"</b> fields with the department name (up to</p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "4 characters) and the course number (up to 3 characters)</p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "and click the <b>\"Add\"</b> button.</p>"

                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "&emsp;The <b>\"Effective Date\"</b> field does not need to be filled</p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "in, as if you create the course with this field blank, it will</p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "be filled with the current date.</p>"

                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "<i>Note: It is not possible to add a course when any cell in the</i></p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "<i>table is selected.</i></p>"

                + "<h1 style='font-family:sans-serif; color:#2A2A2A;'>Removing a Course:</h1>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "&emsp;To remove a course, simply select the course(s) you</p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "want to delete and click the <b>\"Remove\"</b> button.</p>"

                + "<h1 style='font-family:sans-serif; color:#2A2A2A;'>Editing a Course:</h1>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "&emsp;To edit a course, double-click on the cell you want to</p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + "edit. All field rules are also applied during editing through</p>"
                + "<p style='font-family:sans-serif; font-size:12px; color:#4A4A4A;'>"
                + " the table cell.</p>"
                + "</html>");

        textPane.setEditable(false);
        textPane.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(textPane);

        return scrollPane;
    }
}
