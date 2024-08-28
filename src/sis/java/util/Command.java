package util;

import java.io.*;

public class Command {
    private final String command;
    private Process process;
    private final StringBuilder output = new StringBuilder();
    private final StringBuilder errorOutput = new StringBuilder();

    public Command(String command) {
        this.command = command;
    }

    public void execute() throws Exception {
        process = new ProcessBuilder(command).start();
        collectOutput();
        collectErrorOutput();
        process.waitFor();
    }

    private void collectErrorOutput() {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    collectOutput(process.getErrorStream(), errorOutput);
                } catch (IOException e) {
                    errorOutput.append(e.getMessage());
                }
            }
        };
        new Thread(runnable).start();
    }

    private void collectOutput() {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    collectOutput(process.getInputStream(), output);
                } catch (IOException e) {
                    output.append(e.getMessage());
                }
            }
        };
        new Thread(runnable).start();
    }

    private void collectOutput(InputStream inputStream, StringBuilder collector) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
                collector.append(line);
        } finally {
            reader.close();
        }
    }

    public String getOutput() throws IOException {
        return output.toString();
    }

    public String getErrorOutput() throws IOException {
        return output.toString();
    }

    public String toString() {
        return command;
    }
}
