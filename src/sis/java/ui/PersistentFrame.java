package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PersistentFrame extends JFrame {
    static final int DEFAULT_X = 100;
    static final int DEFAULT_Y = 101;
    static final int DEFAULT_WIDTH = 300;
    static final int DEFAULT_HEIGHT = 400;
    private static final String X = "x";
    private static final String Y = "y";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private final Preferences preferences = Preferences.userNodeForPackage(this.getClass());

    public void initialize() {
        int x = preferences.getInt(X, DEFAULT_X);
        int y = preferences.getInt(Y, DEFAULT_Y);
        int width = preferences.getInt(WIDTH, DEFAULT_WIDTH);
        int height = preferences.getInt(HEIGHT, DEFAULT_HEIGHT);

        setBounds(x, y, width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                saveWindowPosition();
            }
        });
    }

    private void saveWindowPosition() {
        Rectangle bounds = getBounds();

        preferences.putInt(X, bounds.x);
        preferences.putInt(Y, bounds.y);
        preferences.putInt(WIDTH, bounds.width);
        preferences.putInt(HEIGHT, bounds.height);
        try {
            preferences.flush();
        }
        catch (BackingStoreException e) {}
    }

    void clearPreferences() throws BackingStoreException {
        preferences.clear();
        preferences.flush();
    }
}