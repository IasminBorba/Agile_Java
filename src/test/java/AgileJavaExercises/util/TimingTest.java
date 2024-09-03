package AgileJavaExercises.util;

import junit.framework.TestCase;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static java.lang.Math.min;

public class TimingTest extends TestCase {
    public void testTiming() {
        int[] sizes = {1, 10, 100, 1000};

        for (int sizeMB : sizes) {
            String fileName = "test_" + sizeMB + "MB.txt";
            try {
                createTestFile(fileName, sizeMB * 1024 * 1024);

                long fileWriterTime = testFileWriter(fileName);
                System.out.println("FileWriter time for " + sizeMB + "MB: " + fileWriterTime + " ms");

                long bufferedWriterTime = testBufferedWriter(fileName);
                System.out.println("BufferedWriter time for " + sizeMB + "MB: " + bufferedWriterTime + " ms");

                double performanceGain = (double) fileWriterTime / bufferedWriterTime;
                System.out.println("Performance gain for " + sizeMB + "MB: " + performanceGain + "x\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createTestFile(String fileName, int sizeInBytes) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[1024];
            new Random().nextBytes(buffer);

            int remainingBytes = sizeInBytes;
            while (remainingBytes > 0) {
                int bytesToWrite = min(buffer.length, remainingBytes);
                fos.write(buffer, 0, bytesToWrite);
                remainingBytes -= bytesToWrite;
            }
        }
    }

    private static long testFileWriter(String fileName) throws IOException {
        long startTime = System.currentTimeMillis();
        try (FileWriter fw = new FileWriter(fileName)) {
            char[] buffer = generateRandomChars();
            int fileSize = (int) Files.size(Paths.get(fileName));

            int iterations = fileSize / buffer.length;
            for (int i = 0; i < iterations; i++)
                fw.write(buffer);
        }
        return System.currentTimeMillis() - startTime;
    }

    private static long testBufferedWriter(String fileName) throws IOException {
        long startTime = System.currentTimeMillis();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            char[] buffer = generateRandomChars();
            int fileSize = (int) Files.size(Paths.get(fileName));

            int iterations = fileSize / buffer.length;
            for (int i = 0; i < iterations; i++)
                bw.write(buffer);
        }
        return System.currentTimeMillis() - startTime;
    }

    private static char[] generateRandomChars() {
        Random random = new Random();
        char[] chars = new char[1024];

        for (int i = 0; i < 1024; i++)
            chars[i] = (char) (random.nextInt(26) + 'a');
        return chars;
    }
}