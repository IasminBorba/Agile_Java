package AgileJavaExercises.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ObjectDumper {
    public int[] testArray1 = {1, 2, 3};
    public String[] testArrayString = {"test1", "test2", "test3"};
    float[][] testArray2 = new float[][]{{1f, 1f}, {2f, 2f}};
    Float[][] testArrayFloat = new Float[][]{{11F}, {22F}};
    String[][][] testArray3 = new String[][][]{{{"Test1.1", "Test1.2"}, {"Test2.1"}}};
    String[][][][] testArray4 = new String[][][][]{{{{"Test1", "test1.2"}, {"test2.2",}, {"teste"}}, {{"test123", "teste"}, {"test5432"}}}};
    Map<String, Integer> map = new HashMap<>();
    StringBuffer buffer = new StringBuffer();

    protected ObjectDumper() {
        this.map.put("map1", 1);
        this.map.put("map2", 2);
        this.map.put("map3", 3);

        this.buffer.append("testBuffer");
    }

    public static <T> String dumper(T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        if (object == null)
            return builder.append("null").toString();

        builder.append("Class ").append(object.getClass().getSimpleName()).append(":\n");

        Field[] fields = object.getClass().getDeclaredFields();
        Class<?> superClass = object.getClass().getSuperclass();
        if (superClass != null) {
            Field[] superFields = superClass.getDeclaredFields();
            builder.append(processFields(fields, object));
            builder.append(processFields(superFields, object));
        } else
            builder.append(processFields(fields, object));
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    public static <T> String processFields(Field[] fields, T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();

        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()))
                continue;

            field.setAccessible(true);
            String flag = field.accessFlags().toString();
            builder.append("\t").append(flag).append(" ");
            Class<?> klass = field.getType();
            String typeName = klass.getSimpleName();
            String name = field.getName();

            if (klass.isArray())
                builder.append(dumperIsArray(typeName, name, object, field));
            else {
                builder.append(typeName).append(" - ").append(name).append(": ");
                if (typeName.equals("StringBuilder") || typeName.equals("StringBuffer"))
                    builder.append("\n").append(hierarchyString(hierarchyString(field.get(object).toString())));
                else {
                    Object value = field.get(object);
                    if (value instanceof Collection)
                        builder.append("\n").append(processCollection((Collection<?>) value));
                    else
                        builder.append(value).append("\n");
                }
            }
        }
        return builder.toString();
    }

    private static String processCollection(Collection<?> collection) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        for (Object item : collection)
            builder.append(hierarchyString(dumper(item)));
        return hierarchyString(builder.toString());
    }

    private static String hierarchyString(String builder) {
        StringBuilder string = new StringBuilder();

        for (String str : builder.split("\n"))
            string.append("\t").append(str).append("\n");
        return string.toString();
    }

    private static String dumperIsArray(String typeName, String nameArray, Object object, Field field) throws IllegalAccessException {
        int dimensions = (int) typeName.chars().filter(ch -> ch == '[').count();
        typeName = "Array " + dimensions + "D of " + typeName.substring(0, typeName.length() - (dimensions * 2));

        StringBuilder builderArrays = new StringBuilder();
        extractValues(field, field.get(object), dimensions, builderArrays);
        return typeName + " - " + nameArray + ":" + hierarchyString(builderArrays.toString()).substring(1);
    }

    private static void extractValues(Field field, Object arrayObject, int dimensions, StringBuilder builder) throws IllegalAccessException {
        if (field.getType().getComponentType().isPrimitive()) {
            builder.append(isPrimitive(field, arrayObject));
            return;
        }
        builder.append("\n");
        for (int i = 0; i < Array.getLength(arrayObject); i++) {
            Object element = Array.get(arrayObject, i);
            if (element != null)
                if (element.getClass().isArray() && dimensions > 1)
                    extractValues(field, element, dimensions - 1, builder.deleteCharAt(builder.length() - 1));
                else
                    if (element.getClass().getName().startsWith("java.") || element.getClass().getName().startsWith("javax."))
                        builder.append("\t").append(element).append(";");
                    else
                        builder.append(hierarchyString(dumper(element) + ";\n"));
            else
                builder.append("\t").append(element).append(";\n");
        }
    }

    private static String isPrimitive(Field field, Object arrayObject) {
        StringBuilder builder = new StringBuilder(" {");

        for (int i = 0; i < Array.getLength(arrayObject); i++)
            builder.append(Array.get(arrayObject, i)).append(", ");
        return builder.delete(builder.length() - 2, builder.length()).append("} \n").toString();
    }
}
