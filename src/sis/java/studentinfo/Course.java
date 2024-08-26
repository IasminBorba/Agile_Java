package studentinfo;

import ui.FieldCatalog;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Course implements java.io.Serializable, Cloneable {
    private String department;
    private String number;
    private LocalDate effectiveDate;

    public Course(String department, String number, String effectiveDate) {
        this.department = department;
        this.number = number;

        try {
            this.effectiveDate = LocalDate.parse(effectiveDate, FieldCatalog.DEFAULT_DATE_FORMAT);
        } catch (Exception e) {
            this.effectiveDate = (LocalDateTime.now().toLocalDate());
        }
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    public String getEffectiveDate() {
        return effectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;

        if (this.getClass() != object.getClass())
            return false;

        Course that = (Course) object;
        return this.department.equals(that.department) && this.number.equals(that.number) && this.effectiveDate.equals(that.effectiveDate);
    }

    @Override
    public int hashCode() {
        final int hashMultiplier = 41;
        int result = 7;
        result = result * hashMultiplier + department.hashCode();
        result = result * hashMultiplier + number.hashCode();
        result = result * hashMultiplier + effectiveDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final String pattern = "MM/dd/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String date = effectiveDate.format(formatter);

        return department + "-" + number + ": " + date;
    }

    public void setDepartment(String newDept) {
        this.department = newDept;
    }

    public void setNumber(String newNumber) {
        this.number = newNumber;
    }

    public void setEffectiveDate(String date) {
        LocalDate newEffectiveDate = LocalDate.parse(date, FieldCatalog.DEFAULT_DATE_FORMAT);
        this.effectiveDate = newEffectiveDate;
    }

    @Override
    public Course clone() {
        Course copy;
        try {
            copy = (Course)super.clone();
        } catch (CloneNotSupportedException impossible) {
            throw new RuntimeException("unable to clone");
        }
        return copy;
    }
}
