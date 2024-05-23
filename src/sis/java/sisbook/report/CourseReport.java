package sisbook.report;

import sisbook.studentinfo.CourseSession;

import java.util.ArrayList;
import java.util.Collections;

import static sisbook.report.RosterReporter.NEWLINE;

public class CourseReport {
    private final ArrayList<CourseSession> sessions = new ArrayList<>();

    public void add(CourseSession session){
        sessions.add(session);
    }

    public String text(){
        Collections.sort(sessions);
        StringBuilder builder = new StringBuilder();
        for(CourseSession session: sessions)
            builder.append(session.getDepartment()).append(" ").append(session.getNumber()).append(NEWLINE);
        return builder.toString();
    }
}
