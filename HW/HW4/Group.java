/**
 * Represents an immutable group of people.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class Group {
    private final String[] people;

    /**
     * Constructs a Group from a defensive copy of the given array of names.
     *
     * @param people String[] representing the names to include in this group.
     */
    public Group(String[] people) {
        if (people == null) {
            this.people = new String[0];
        } else {
            String[] p = new String[people.length];
            for (int i = 0; i < people.length; i++) {
                p[i] = people[i];
            }
            this.people = p;
        }
    }

    /**
     * Returns the number of people in this group.
     *
     * @return int representing the size of this group.
     */
    public int size() {
        return this.people.length;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.size(); i++) {
            boolean hasSlash = i != this.size() - 1;
            result += this.people[i] + (hasSlash ? "/" : "");
        }
        return result;
    }
}
