package util;

import javax.swing.*;

public class ImageUtil {
    public static ImageIcon create(String path) {
        java.net.URL imageURL = ImageUtil.class.getClassLoader().getResource(path);
        if (imageURL == null) {
            return null;
        }
        return new ImageIcon(imageURL);
    }
}

