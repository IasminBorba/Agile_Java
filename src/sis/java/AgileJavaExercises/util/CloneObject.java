package AgileJavaExercises.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CloneObject {
    public static Object shallowClone(Object object) throws Exception {
        Class<?> classObj = object.getClass();
        Constructor<?> constructorObj = classObj.getDeclaredConstructor();
        constructorObj.setAccessible(true);

        Object clone = constructorObj.newInstance();

        copyFields(object, clone, classObj);
        return clone;
    }

    private static void copyFields(Object obj, Object clone, Class<?> klass) throws IllegalAccessException {
        if (klass.getSuperclass() != null)
            copyFields(obj, clone, klass.getSuperclass());

        for (Field field : klass.getDeclaredFields()) {
            field.setAccessible(true);
            field.set(clone, field.get(obj));
        }
    }
}
