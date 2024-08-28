package util;

import java.util.ResourceBundle;

public class Bundle {
    static String DEFAULT_BASE_NAME = "Messages";
    private static ResourceBundle bundle;

    static String getName() {
        return DEFAULT_BASE_NAME;
    }

    static void setName(String name) {
        DEFAULT_BASE_NAME = name;
        bundle = null;
    }

    public static String get(String key) {
        if (bundle == null)
            loadBundle();
        return bundle.getString(key);
    }

    private static void loadBundle() {
        System.out.println("load");
        System.out.println(getName());
        bundle = ResourceBundle.getBundle("util." + getName());
    }
}
