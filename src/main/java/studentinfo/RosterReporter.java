package studentinfo;

public class RosterReporter {

    static final String NEWLINE = System.lineSeparator();
    static final String ROSTER_REPORT_HEADER = "Student" + NEWLINE + "-" + NEWLINE;
    static final String ROSTER_REPORT_FOOTER = NEWLINE + "# students = ";
    private final CourseSession session;

    RosterReporter(CourseSession session) {
        this.session = session;
    }

    String getReport() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(ROSTER_REPORT_HEADER);
        for(Student student: session.getAllStudents()){
            buffer.append(student.getName());
            buffer.append(NEWLINE);
        }
        buffer.append(ROSTER_REPORT_FOOTER).append(session.getAllStudents().size()).append(NEWLINE);
        return buffer.toString();
    }
}
