package report;

import studentinfo.CourseSession;
import studentinfo.Session;
import studentinfo.Student;

public class RosterReporter {

    static final String NEWLINE = System.lineSeparator();
    static final String ROSTER_REPORT_HEADER = STR."Student\{NEWLINE}-\{NEWLINE}";
    static final String ROSTER_REPORT_FOOTER = STR."\{NEWLINE}# students = ";
    private final Session session;

    RosterReporter(Session session) {
        this.session = session;
    }

    String getReport() {
        StringBuilder buffer = new StringBuilder();
        writeHeader(buffer);
        writeBody(buffer);
        writeFooter(buffer);
        return buffer.toString();
    }

    void writeHeader(StringBuilder buffer){
        buffer.append(ROSTER_REPORT_HEADER);
    }

    void writeBody(StringBuilder buffer){
        for(Student student: session.getAllStudents()){
            buffer.append(student.getName());
            buffer.append(NEWLINE);
        }
    }

    void writeFooter(StringBuilder buffer){
        buffer.append(ROSTER_REPORT_FOOTER).append(session.getAllStudents().size()).append(NEWLINE);
    }
}
