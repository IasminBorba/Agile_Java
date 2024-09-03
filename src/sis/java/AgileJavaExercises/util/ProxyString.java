package AgileJavaExercises.util;

import java.io.IOException;

public interface ProxyString {
    String read() throws IOException;

    void write(String text) throws IOException;

    void delete();

    void overwrite(String textOverWrite) throws IOException;

    String toString();
}
