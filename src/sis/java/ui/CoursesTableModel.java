package ui;

import studentinfo.Course;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.*;

class CoursesTableModel extends AbstractTableModel {
    private final List<Course> courses = new ArrayList<>();
    private final SimpleDateFormat formatter = new SimpleDateFormat(("MM/dd/yy"));
    private final FieldCatalog catalog = new FieldCatalog();
    private final String[] fields = {
            FieldCatalog.DEPARTMENT_FIELD_NAME,
            FieldCatalog.NUMBER_FIELD_NAME,
            FieldCatalog.EFFECTIVE_DATE_FIELD_NAME
    };

    void add(Course course) {
        courses.add(course);
        fireTableRowsInserted(courses.size()-1, courses.size());
    }

    void remove(Course course) {
        int index = courses.indexOf(course);
        if (index != -1) {
            courses.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    void update(Course course, Course newCourse) {
        int index = getIndexCourse(course);
        courses.set(index, newCourse);
        fireTableRowsUpdated(index, index);
    }


    Course get(int index) {
        return courses.get(index);
    }

    int getIndexCourse(Course course) {
        int index = 0;
        for (Course c : courses) {
            if (c.equals(course))
                return index;
            index++;
        }
        return -1;
    }

    public String getColumnName(int column) {
        Field field = catalog.get(fields[column]);
        return field.getShortName();
    }

    public Object getValueAt(int row, int column) {
        Course course = courses.get(row);
        String fieldName = fields[column];
        return switch (fieldName) {
            case FieldCatalog.DEPARTMENT_FIELD_NAME -> course.getDepartment();
            case FieldCatalog.NUMBER_FIELD_NAME -> course.getNumber();
            case FieldCatalog.EFFECTIVE_DATE_FIELD_NAME -> course.getEffectiveDate();
            default -> "";
        };
    }

    public int getColumnCount() {
        return fields.length;
    }

    public int getRowCount() {
        return courses.size();
    }

    public List<Course> getCourses() {
        return courses;
    }
}
