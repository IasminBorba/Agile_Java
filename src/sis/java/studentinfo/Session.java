package studentinfo;

import java.io.*;
import java.util.*;
import java.net.*;

abstract public class Session implements Comparable<Session>, Iterable<Student>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private final Course course;
    private transient List<Student> students = new ArrayList<>();
    private final Date startDate;
    private int numberOfCredits;
    private URL url;

    protected Session(Course course, Date startDate) {
        this.course = course;
        this.startDate = startDate;
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }

    public int compareTo(Session that) {
        int compare = this.getDepartment().compareTo(that.getDepartment());
        if (compare != 0)
            return compare;
        return this.getNumber().compareTo(that.getNumber());
    }

    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getDepartment() {
        return course.getDepartment();
    }

    public String getNumber() {
        return course.getNumber();
    }

    int getNumberOfStudents() {
        return students.size();
    }

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }

    double averageGpaForPartTimeStudents() {
        double total = 0.0;
        int count = 0;

        for (Student student : students) {
            if (student.isFullTime())
                continue;
            count++;
            total += student.getGpa();
        }
        return (count == 0) ? 0.0 : total / count;
    }

    Student get(int index) {
        return students.get(index);
    }

    protected Date getStartDate() {
        return startDate;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    abstract protected int getSessionLength();

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    public void setUrl(String urlString) throws SessionException {
        try {
            this.url = new URL(urlString);
        } catch (MalformedURLException e) {
            log(e);
            throw new SessionException(e);
        }
    }

    private void log(Exception e) {
        e.printStackTrace();
    }

    public URL getUrl() {
        return url;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    @Serial
    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        output.writeInt(students.size());
        for (Student student : students)
            output.writeObject(student.getLastName());
    }

    @Serial
    private void readObject(ObjectInputStream input) throws Exception {
        input.defaultReadObject();
        students = new ArrayList<>();
        int size = input.readInt();
        for (int i = 0; i < size; i++) {
            String lastName = (String) input.readObject();
            students.add(Student.findByLastName(lastName));
        }
    }
}