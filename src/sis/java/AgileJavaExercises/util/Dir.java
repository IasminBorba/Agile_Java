package AgileJavaExercises.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Dir {
    File directory;
    String dirName;
    String path;
    ArrayList<MyFile> myFiles = new ArrayList<>();

    public Dir(String filename) throws IOException {
        File fileExist = new File(System.getProperty("user.dir") + File.separator + filename);
        if (!fileExist.isDirectory())
            throw new IOException("Directory not already exists");
        else {
            if (fileExist.isFile())
                throw new IOException("The directory name is the same as a file name");
            else {
                this.directory = fileExist;
                this.dirName = directory.getName();
                this.path = directory.getPath();
            }
        }
    }

    public static Dir ensureExists(String filename) throws IOException {
        File DirNotExist = new File(System.getProperty("user.dir") + File.separator + filename);
        try {
            DirNotExist.mkdir();
        } finally {
            return new Dir(filename);
        }
    }

    public MyFile addMyFile(String filename) {
        File fileAux = new File(directory, filename);
        MyFile myFile = new MyFile(new File(directory, filename).getPath());
        myFiles.add(myFile);
        return myFile;
    }

    public int getMyFiles() throws Exception {
        if (!this.directory.isDirectory())
            throw new IOException("Directory not already exists");
        else
            return myFiles.size();
    }

    public Attributes getAttributes() {
        return new Attributes(directory.canWrite(), directory.isHidden());
    }

    public static class Attributes implements Serializable {
        boolean isReadOnly;

        boolean isHidden;

        Attributes(boolean isReadOnly, boolean isHidden) {
            this.isReadOnly = isReadOnly;
            this.isHidden = isHidden;
        }
    }
}
