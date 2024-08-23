package ui;

import junit.framework.TestCase;
import javax.swing.*;
import util.*;

public class StatusBarTest extends TestCase {
    private JTextField field1;
    private JTextField field2;
    private StatusBar statusBar;
    private JFrame frame;
    protected void setUp() {
        field1 = new JTextField(10);
        field2 = new JTextField(10);
        statusBar = new StatusBar();
        JPanel panel = SwingUtil.createPanel(field1, field2, statusBar);
        frame = SwingUtil.createFrame(panel);
    }
    protected void tearDown() {
        frame.dispose();
    }

    public void testInfo() {
        statusBar.setInfo(field1, "a");
        assertEquals("a", statusBar.getInfo(field1));
    }
}