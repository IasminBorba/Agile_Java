package AgileJavaExercises.util;

import java.io.*;

public class SerializableObj implements Serializable {
    private final String nameClass;
    private final double valorClass;
    private final int seqClass;

    public SerializableObj(String nameClass, double valorClass, int seqClass) {
        this.nameClass = nameClass;
        this.valorClass = valorClass;
        this.seqClass = seqClass;
    }

    public String getName() {
        return nameClass;
    }

    public double getValor() {
        return valorClass;
    }

    public int getSeq() {
        return seqClass;
    }

    public static SerializableObj serializableObjCopy(SerializableObj obj) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(byteOut);
            output.writeObject(obj);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream input = new ObjectInputStream(byteIn);
            return (SerializableObj) input.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public SerializableObj copyObj(String filename) {
        SerializableObj obj = null;
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));
            output.writeObject(this);
            output.close();

            ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename));
            obj = (SerializableObj) input.readObject();
        } finally {
            return obj;
        }
    }
}
