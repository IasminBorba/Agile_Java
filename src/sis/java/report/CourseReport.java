package report;

import studentinfo.CourseSession;
import studentinfo.Session;

import java.util.ArrayList;

public class CourseReport {
    private final ArrayList<CourseSession> sessions = new ArrayList<>();

    public void add(Session session){
        sessions.add((CourseSession)session);
    }

    public String text(){
//        Collections.sort(sessions);
        StringBuilder builder = new StringBuilder();
        for(CourseSession session: sessions)
            builder.append(session.getDepartment()).append(" ").append(session.getNumber()).append(RosterReporter.NEWLINE);
        return builder.toString();
    }
}
