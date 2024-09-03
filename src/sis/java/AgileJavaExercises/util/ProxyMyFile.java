package AgileJavaExercises.util;

import java.io.IOException;

import static AgileJavaExercises.util.ObjectDumper.dumper;

public class ProxyMyFile implements ProxyString {
    private final MyFile object;

    public ProxyMyFile(String filename) {
        this.object = new MyFile(filename);
    }

    public String read() throws IOException {
        return object.read();
    }

    public void write(String text) throws IOException {
        object.write(text);
    }

    public void delete() {
        object.delete();
    }

    public void overwrite(String textOverWrite) throws IOException {
        object.overwrite(textOverWrite);
    }

    @Override
    public String toString() {
        try {
            return dumper(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
