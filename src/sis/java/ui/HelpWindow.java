package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

public class HelpWindow extends JFrame{
    static final int WIDTH = 300;
    static final int HEIGHT = 150;
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
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(createTextPanel());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setLayout(new BorderLayout());

        final int pad = 6;
        Border emptyBorder = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border titleBorder = BorderFactory.createTitledBorder(bevelBorder);
        panel.setBorder(BorderFactory.createCompoundBorder(emptyBorder, titleBorder));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    JScrollPane createTextPanel() {
        String longText = "<html>"
                + "<h2>Adding a Course:</h2>"
                + "<p>&emsp;To add a course, you need to fill in the b>\"Department\"</b> and <b>\"Number\"</b> fields with the department name (up to 4 characters) and the course number (up to 3 characters) and click the <b>\"Add\"</b> button.</p>"
                + "<p>&emsp;The <b>\"Effective Date\"</b> field does not need to be filled in, as if you create the course with this field blank, it will be filled with the current date.</p>"
                + "<p><i>Note: It is not possible to add a course when any cell in the table is selected.</i></p>"
                + "<h2>Removing a Course:</h2>"
                + "<p>&emsp;To remove a course, simply select the course(s) you want to delete and click the <b>\"Remove\"</b> button.</p>"
                + "<h2>Editing a Course:</h2>"
                + "<p>&emsp;To edit a course, double-click on the cell you want to edit. All field rules are also applied during editing through the table cell.</p>"
                + "</html>";

        JLabel label = new JLabel(longText);
        label.setVerticalAlignment(JLabel.TOP);

        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        return scrollPane;
    }
}
