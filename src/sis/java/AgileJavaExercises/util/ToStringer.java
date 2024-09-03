package AgileJavaExercises.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ToStringer {
    private final Object obj;
    Map<Integer, Field> dumpFields = new HashMap<>();
    Set<Field> notDumpFields = new HashSet<>();

    public <T> ToStringer(T object) {
        this.obj = object;
    }

    protected <T> String toString(T object) throws Exception {
        StringBuilder builder = new StringBuilder(object.getClass().getSimpleName()).append(" fields annotation @Dump:\n");
        for (Field field : getFields()) {
            field.setAccessible(true);
            builder.append("\t").append(field.getName()).append(": ");

            Dump dump = field.getAnnotation(Dump.class);
            Object value = field.get(object);

            value = processFieldValue(value, dump);

            if (dump != null && dump.quote())
                builder.append("\"").append(value).append("\"");
            else
                builder.append(value);

            builder.append("\n");
        }

        return removeTrailingNewline(builder);
    }

    private Object processFieldValue(Object value, Dump dump) {
        if (dump != null && value != null && dump.outputMethods() != null) {
            StringBuilder builderMethods = new StringBuilder();
            try {
                for (String method : dump.outputMethods())
                    builderMethods.append("\t").append(invokeMethod(value, method)).append("\n");

                if (!builderMethods.isEmpty()) {
                    builderMethods.deleteCharAt(0).deleteCharAt(builderMethods.length() - 1);
                    value = builderMethods.toString();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    private Object invokeMethod(Object value, String methodName) throws NoSuchMethodException {
        try {
            Method method = value.getClass().getMethod(methodName);
            return method.invoke(value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String removeTrailingNewline(StringBuilder builder) {
        if (!builder.isEmpty() && builder.charAt(builder.length() - 1) == '\n')
            builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }


    private ArrayList<Field> getFields() {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Dump.class)) {
                Dump dump = field.getAnnotation(Dump.class);
                dumpFields.put(checkOrder(dump.order()), field);
            } else
                notDumpFields.add(field);
        }
        return sortList();
    }

    private ArrayList<Field> sortList() {
        dumpFields = new TreeMap<>(dumpFields);
        ArrayList<Field> listFields = new ArrayList<>(dumpFields.values());
        listFields.addAll(notDumpFields);
        return listFields;
    }

    private int checkOrder(int order) {
        for (int orderExists : dumpFields.keySet())
            if (orderExists == order)
                return checkOrder(order - 1);

        return order;
    }
}