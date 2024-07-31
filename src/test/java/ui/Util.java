package ui;

import javax.swing.*;
import java.awt.*;

public class Util {
    static Component getComponent(Container container, String name) {
        for (Component component: container.getComponents())
            if (name.equals(component.getName()))
                return component;
        return null;
    }

    static Component getComponent(JFrame frame, String name) {
        return getComponent(frame.getContentPane(), name);
    }
}