package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LineWriter {
    public static void write(String filename, String[] records) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
        }finally {
            if (writer != null)
                writer.close();
        }
    }
}
