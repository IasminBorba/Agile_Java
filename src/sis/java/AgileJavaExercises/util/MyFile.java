package AgileJavaExercises.util;

import java.io.*;

public class MyFile {
    File file;
    String filename;

    public MyFile(String filename) {
        this.file = new File(filename);
        this.filename = filename;
    }

    public String read() throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null)
                builder.append(String.format(line + "%n"));
            builder.deleteCharAt(builder.length() - 1);

        }
        return builder.toString();
    }

    public void write(String text) throws IOException {
        if (file.exists())
            throw new IOException("File already exists: " + filename);

        try (Writer writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(text);
        }
    }

    public void delete() {
        file.delete();
    }

    public void overwrite(String textOverWrite) throws IOException {
        try (Writer writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(textOverWrite);
        }
    }
}
