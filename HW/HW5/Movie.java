/**
 * Represents a movie available for rental, extending {@link Media} with a runtime field.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class Movie extends Media {
    private int runtime;

    /**
     * Constructs a Movie with all fields specified.
     *
     * @param genre       Genre representing the genre of the movie
     * @param name        String representing the title of the movie
     * @param rating      int representing the rating score of the movie
     * @param rentalPrice double representing the price to rent the movie
     * @param runtime     int representing the runtime of the movie in minutes
     */
    public Movie(Genre genre, String name, int rating, double rentalPrice, int runtime) {
        super(genre, name, rating, rentalPrice);
        this.runtime = runtime;
    }

    /**
     * Constructs a Movie with a default rental price of $5.00 and runtime of 111 minutes.
     *
     * @param genre  Genre representing the genre of the movie
     * @param name   String representing the title of the movie
     * @param rating int representing the rating score of the movie
     */
    public Movie(Genre genre, String name, int rating) {
        this(genre, name, rating, 5.0, 111);
    }

    @Override
    public String toString() {
        return String.format("%s, Runtime: %d", super.toString(), this.runtime);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || !(other instanceof Movie)) {
            return false;
        }

        Movie otherMovie = (Movie) other;
        return super.equals(otherMovie) && this.runtime == otherMovie.runtime;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + this.runtime;
        return result;
    }
}
