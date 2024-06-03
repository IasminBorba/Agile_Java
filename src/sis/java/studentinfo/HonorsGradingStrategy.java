package studentinfo;


public class HonorsGradingStrategy implements GradingStrategy {
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointsFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }
    int basicGradePointsFor(Student.Grade grade) {
        return switch (grade) {
            case A -> 4;
            case B -> 3;
            case C -> 2;
            case D -> 1;
            default -> 0;
        };
    }
}
