package studentinfo;

import java.util.ArrayList;
import java.util.List;

public class Name {
    protected final String firstName;
    protected String middleName = "";
    protected final String lastName;
    protected final String fullName;

    Name(String fullName) {
        this.fullName = fullName;
        List<String> names = split(fullName);

        this.firstName = names.getFirst();
        this.lastName = names.getLast();
        if (names.size() > 2)
            this.middleName = names.get(1);
    }

    protected List<String> split(String fullName) {
        List<String> results = new ArrayList<>();
        for (String name : fullName.split("\\s+"))
            results.add(name);
        return results;
    }
}
