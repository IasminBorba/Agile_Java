package sisbook.report;

import sisbook.studentinfo.Student;

import java.util.ArrayList;

public class ReportCard {
    private final ArrayList<Student> cards = new ArrayList<>();

    public void add(Student card){
        cards.add(card);
    }

    public int textGPA(){
        int GPA = cards.getFirst().GPA;
        return GPA/2;
    }
}
