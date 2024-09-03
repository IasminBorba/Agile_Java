package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.io.*;
import java.util.Objects;

import static AgileJavaExercises.util.Dir.ensureExists;

public class DirTest extends TestCase {
    String str = "Mais aventuras em classes utilitárias: " +
            "Crie uma classe Dir que encapsule um objeto File que, " +
            "por sua vez, representa um diretório real do sistema de arquivos. " +
            "Projete a classe de forma que ela seja funcional apenas quando mapeada para um diretório existente. " +
            "Forneça um método chamado ensureExists para criar o diretório se ele não existir. " +
            "O construtor deve lançar uma exceção se o nome do diretório for o mesmo que o de um arquivo " +

            "Finalmente, forneça um método que retorne uma lista de objetos MyFile " +
            "no diretório e lance uma exceção se o diretório ainda não tiver sido criado.";

    public void testCreate() throws Exception {
        String dirName = "testDir";
        Dir dir = null;

        try {
            dir = new Dir(dirName);
        } catch (IOException e) {
            if (Objects.equals(e.getMessage(), "Directory not already exists"))
                dir = ensureExists(dirName);
            else
                System.out.println(e.getMessage());
        }
        assertEquals(dirName, dir.dirName);
        assertEquals(0, dir.getMyFiles());
    }

    public void testMyFiles() throws Exception {
        String dirName = "testDir";
        Dir dir = null;

        try {
            dir = new Dir(dirName);
        } catch (IOException e) {
            if (Objects.equals(e.getMessage(), "Directory not already exists"))
                dir = ensureExists(dirName);
            else
                System.out.println(e.getMessage());
        }

        assertEquals("testDir", dir.dirName);
        assertEquals(dir.dirName, dir.directory.getName());
        assertEquals(dir.path, dir.directory.getPath());
        assertEquals(0, dir.getMyFiles());


        MyFile myFile1 = dir.addMyFile("file1.txt");
        myFile1.write("test1");
        MyFile myFile2 = dir.addMyFile("file2.txt");
        myFile2.write("test2");
        MyFile myFile3 = dir.addMyFile("file3.txt");
        myFile3.write("test3");


        assertEquals(3, dir.getMyFiles());
        assertTrue(dir.myFiles.contains(myFile1));
        assertTrue(dir.myFiles.contains(myFile2));
        assertTrue(dir.myFiles.contains(myFile3));

        myFile1.delete();
        myFile2.delete();
        myFile3.delete();
    }

    public static void main(String[] args) {
        DirTest test = new DirTest();
        test.runTests();
    }

    public void runTests() {
        System.out.println("Test without OutputStreamWriter:");
        captureExceptionWithoutOutputStreamWriter();

        System.out.println("\nTest with OutputStreamWriter:");
        captureExceptionWithOutputStreamWriter();
    }

    private void captureExceptionWithoutOutputStreamWriter() {
        try {
            throw new Exception("Exception of test");
        } catch (Exception e) {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                e.printStackTrace(new PrintStream(byteArrayOutputStream));
                String stackTrace = byteArrayOutputStream.toString();
                System.out.println(stackTrace);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private void captureExceptionWithOutputStreamWriter() {
        try {
            throw new Exception("Exception of test");
        } catch (Exception e) {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(byteArrayOutputStream);
                 PrintWriter pw = new PrintWriter(osw)) {

                e.printStackTrace(pw);
                pw.flush();
                String stackTrace = byteArrayOutputStream.toString();
                System.out.println(stackTrace);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void testInstantiateObject() throws IOException {
        Dir dir = new Dir("testDir");
        try {
            Dir.Attributes attributes = new Dir.Attributes(true, false);      // Dar erro na compilação caso Attributes não fosse estatica
            Dir.Attributes attributesTrue = dir.getAttributes();             // Funciona
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
