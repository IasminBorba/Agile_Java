package studentinfo;

public class Entry {
    private final Student.Grade grade;
    private final String message;

    public Entry(Student.Grade grade, String message){
        this.grade = grade;
        this.message = message;
    }

    @Override
    public boolean equals(Object object){
        if(object.getClass() != this.getClass())
            return false;

        Entry that = (Entry) object;
        return this.grade == that.grade && this.message.equals(that.message);
    }

    @Override
    public int hashCode(){
        final int hashMultiplier = 41;
        int result = 7;
        result = result * hashMultiplier + grade.hashCode();
        result = result * hashMultiplier + message.hashCode();
        return result;
    }
}
