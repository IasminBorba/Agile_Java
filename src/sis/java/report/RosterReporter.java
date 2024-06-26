package report;

import studentinfo.*;
import java.io.*;

import static java.lang.String.format;

public class RosterReporter {
    static final String ROSTER_REPORT_HEADER = "Student%n-%n";
    static final String ROSTER_REPORT_FOOTER = "%n# students = %d%n";
    private final Session session;
    private Writer writer;

    RosterReporter(Session session) {
        this.session = session;
    }

    void writeReport(Writer writer) throws IOException{
        this.writer = writer;
        writeHeader();
        writeBody();
        writeFooter();
    }

    private void writeHeader() throws IOException{
        writer.write(format(ROSTER_REPORT_HEADER));
    }

    void writeBody() throws IOException{
        for(Student student: session.getAllStudents()){
            writer.write(format(student.getName() + "%n"));
        }
    }

    private void writeFooter() throws IOException{
        writer.write(format(ROSTER_REPORT_FOOTER, session.getAllStudents().size()));
    }
}
