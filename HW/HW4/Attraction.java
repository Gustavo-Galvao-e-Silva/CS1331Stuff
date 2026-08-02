/**
 * Represents an attraction that admits groups of visitors and tracks ratings.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class Attraction implements Admittable, Comparable<Attraction> {
    private final String name;
    private long sumRatings = 0;
    private int numRatings = 0;
    private final double admissionFee;
    private Group[] visitors = new Group[5];

    /**
     * Constructs an Attraction with the given name and admission fee.
     *
     * @param name         String representing the name of the attraction.
     * @param admissionFee double representing the admission fee in dollars.
     */
    public Attraction(String name, double admissionFee) {
        this.name = (name == null || name.isBlank()) ? "No name" : name;
        this.admissionFee = (admissionFee < 0) ? 0.0 : admissionFee;
    }

    /**
     * Constructs an Attraction with the given name and a default admission fee of
     * 5.25 dollars.
     *
     * @param name String representing the name of the attraction.
     */
    public Attraction(String name) {
        this(name, 5.25);
    }

    @Override
    public void admit(String[] visitorArray) {
        if (visitors.length == 0) {
            return;
        }
        for (int i = 0; i < visitors.length; i += 5) {
            int emptyIndex = firstEmptyVisitorIndex();
            if (emptyIndex == -1) {
                emptyIndex = this.visitors.length;
                doubleVisitorsSize();
            }
            this.visitors[emptyIndex] = createMaximumGroup(i, visitorArray);
        }
    }

    /**
     * Records a rating for the group at the given index and removes them from the
     * attraction.
     *
     * @param groupIndex int representing the index of the group in the visitors
     *                   array.
     * @param rating     int representing the rating to record, normalized to range
     *                   [1, 10].
     * @return the number of visitors removed, or -1 if the index was invalid.
     */
    public int rateAndExit(int groupIndex, int rating) {
        if (groupIndex < 0 || groupIndex > this.visitors.length || this.visitors[groupIndex] == null) {
            System.out.println("Could not update rating. Index invalid.");
            return -1;
        }
        if (rating < 1) {
            rating = 1;
        } else if (rating > 10) {
            rating = 10;
        }
        this.numRatings++;
        this.sumRatings += rating;
        int visitorsRemoved = this.visitors[groupIndex].size();
        this.visitors[groupIndex] = null;
        this.shiftGroups(groupIndex);
        return visitorsRemoved;
    }

    /**
     * Returns the average rating across all recorded ratings, rounded to two
     * decimal places.
     *
     * @return float representing the average rating.
     */
    public double averageRating() {
        return roundToTwoDecimals((double) this.sumRatings / this.numRatings);
    }

    /**
     * Prints all current visitor groups to standard output.
     */
    public void printVisitors() {
        System.out.println(this.toString());
        int nonNullGroupCounter = 1;
        for (int i = 0; i < this.visitors.length; i++) {
            if (this.visitors[i] != null) {
                System.out.printf("Group %d: %s%n", nonNullGroupCounter++, this.visitors[i].toString());
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s/%.2f/%.2f", this.name, this.averageRating(), this.admissionFee);
    }

    @Override
    public int compareTo(Attraction otherAttraction) {
        if (otherAttraction == null) {
            return -1;
        }

        if (this.averageRating() > otherAttraction.averageRating()) {
            return 1;
        }

        if (this.averageRating() < otherAttraction.averageRating()) {
            return -1;
        }

        if (this.admissionFee > otherAttraction.admissionFee) {
            return 1;
        }

        if (this.admissionFee < otherAttraction.admissionFee) {
            return -1;
        }

        return 0;
    }

    private int firstEmptyVisitorIndex() {
        for (int i = 0; i < this.visitors.length; i++) {
            if (this.visitors[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private void doubleVisitorsSize() {
        Group[] result = new Group[2 * this.visitors.length];
        for (int i = 0; i < this.visitors.length; i++) {
            result[i] = this.visitors[i];
        }
        this.visitors = result;
    }

    private Group createMaximumGroup(int startingIndex, String[] visitorArray) {
        int endingIndex = min(startingIndex + 5, visitorArray.length);
        String[] maximumVisitor = new String[endingIndex - startingIndex];
        int maximumVisitorIndex = 0;
        for (int i = startingIndex; i < endingIndex; i++) {
            maximumVisitor[maximumVisitorIndex++] = visitorArray[i];
        }
        return new Group(maximumVisitor);
    }

    private void shiftGroups(int startingIndex) {
        for (int curr = startingIndex; curr < this.visitors.length - 1; curr++) {
            int next = curr + 1;
            if (this.visitors[curr] == null && this.visitors[next] != null) {
                this.visitors[curr] = this.visitors[next];
                this.visitors[next] = null;
            }
        }
    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }

    protected static double roundToTwoDecimals(double a) {
        return (double) Math.round(a * 100) / 100;
    }
}
