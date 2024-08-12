package report;

import studentinfo.*;

import java.util.*;

public class CourseReport {
    private final ArrayList<CourseSession> sessions = new ArrayList<>();

    public void add(Session session) {
        sessions.add((CourseSession) session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder builder = new StringBuilder();
        for (CourseSession session : sessions)
            builder.append(String.format(session.getDepartment() + " " + session.getNumber() + "%n"));

        return builder.toString();
    }
}
