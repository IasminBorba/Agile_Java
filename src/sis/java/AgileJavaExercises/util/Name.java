package AgileJavaExercises.util;

public class Name {
    public String name;

    Name(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (this == object)
            return true;
        if (this.getClass() != object.getClass())
            return false;

        Name that = (Name) object;

        return this.name.equals(that.name);
    }
}
