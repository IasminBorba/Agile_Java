package AgileJavaExercises.util;

import java.io.*;
import java.util.ArrayList;

public class BaseSize implements Serializable {
    public static byte[] bytesSeq(File file) throws IOException {
        DataInputStream input = null;
        byte[] bytes;
        try {
            input = new DataInputStream(new FileInputStream(file.getPath()));
            bytes = input.readAllBytes();
        } finally {
            input.close();
        }
        return bytes;
    }

    public <T> byte[] baseTest(File file, T type) throws IOException {
        DataOutputStream output = null;
        ArrayList<byte[]> types = new ArrayList<>();
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            switch (type.getClass().getSimpleName()) {
                case "Character" -> output.writeChar((char) type);
                case "Integer" -> output.writeInt((int) type);
                case "Double" -> output.writeDouble((double) type);
                case "Long" -> output.writeLong((long) type);
                case "Float" -> output.writeFloat((float) type);
                case "Short" -> output.writeShort((short) type);
            }
            bytes = bytesSeq(file);
        } finally {
            output.close();
        }
        return bytes;
    }
}