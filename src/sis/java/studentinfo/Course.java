package studentinfo;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Course implements java.io.Serializable{
    private final String department;
    private final String number;
    private final LocalDate effectiveDate;

    public Course(String department, String number){
        this.department = department;
        this.number = number;
        this.effectiveDate = LocalDateTime.now().toLocalDate();
    }

    public Course(String department, String number, LocalDate effectiveDate){
        this.department = department;
        this.number = number;
        this.effectiveDate = effectiveDate;
    }

    public String getDepartment(){
        return department;
    }

    public String getNumber(){
        return number;
    }

    public LocalDate getEffectiveDate(){
        return effectiveDate;
    }

    @Override
    public boolean equals(Object object){
        if(object == null)
            return false;

        if(this.getClass() != object.getClass())
            return false;

        Course that = (Course) object;
        return this.department.equals(that.department) && this.number.equals(that.number) && this.effectiveDate.equals(that.effectiveDate);
    }

    @Override
    public int hashCode(){
        final int hashMultiplier = 41;
        int result = 7;
        result = result * hashMultiplier + department.hashCode();
        result = result * hashMultiplier + number.hashCode();
        result = result * hashMultiplier + effectiveDate.hashCode();
        return result;
    }

    @Override
    public String toString(){
        final String pattern = "MM/dd/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String date = effectiveDate.format(formatter);

        return department + "-" + number + ": " + date;
    }
}
