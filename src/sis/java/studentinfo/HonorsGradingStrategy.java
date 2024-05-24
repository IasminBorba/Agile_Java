package studentinfo;

public class HonorsGradingStrategy implements GradingStrategy {
    public int getGradePointsFor(Student.Grade grade){
        int points = RegularGradingStrategy.basicGradePointsFor(grade);
        if (points > 0){
            points += 1;
        }
        return points;
    }
}
