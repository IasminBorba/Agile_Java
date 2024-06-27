package studentinfo;

import java.io.Serializable;

public class BasicGradingStrategy implements GradingStrategy, Serializable {
    public int getGradePointsFor(Student.Grade grade) {
        return grade.getPoints();
    }
}
