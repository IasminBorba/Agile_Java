package ui;

import javax.swing.*;
import java.awt.*;

public class Util {
    static Component getComponent(Container container, String name) {
        for (Component component: container.getComponents()) {
            if (name.equals(component.getName()))
                return component;
            if (component instanceof Container subcontainer) {
                Component subcomponent = getComponent(subcontainer, name);
                if (subcomponent != null)
                    return subcomponent;
            }
        }
        return null;
    }

    static Component getComponent(JFrame frame, String name) {
        return getComponent(frame.getContentPane(), name);
    }
}