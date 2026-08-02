/**
 * Abstract base class representing a rentable media item.
 * Implements Comparable to allow ordering by genre, name, and rating.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public abstract class Media implements Comparable<Media> {
    private Genre genre;
    private String name;
    private int rating;
    private double rentalPrice;

    /**
     * Constructs a Media item with all fields specified.
     *
     * @param genre       Genre representing the genre of the media
     * @param name        String representing the title of the media
     * @param rating      int representing the rating score of the media
     * @param rentalPrice double representing the price to rent the media
     */
    public Media(Genre genre, String name, int rating, double rentalPrice) {
        this.genre = genre;
        this.name = name;
        this.rating = rating;
        this.rentalPrice = rentalPrice;
    }

    /**
     * Constructs a Media item with a default rental price of $7.00.
     *
     * @param genre  Genre representing the genre of the media
     * @param name   String representing the title of the media
     * @param rating int representing the rating score of the media
     */
    public Media(Genre genre, String name, int rating) {
        this(genre, name, rating, 7.0);
    }

    @Override
    public String toString() {
        return String.format("Genre: %s, Name: %s, Rating: %d, Rental Price: $%.2f", this.genre.toString(),
                this.name, this.rating, this.rentalPrice);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || !(other instanceof Media)) {
            return false;
        }

        Media otherMedia = (Media) other;
        return this.genre.equals(otherMedia.genre)
            && this.name.equals(otherMedia.name)
            && this.rating == otherMedia.rating
            && this.rentalPrice == otherMedia.rentalPrice;
    }

    @Override
    public int compareTo(Media otherMedia) {
        if (this.genre.ordinal() > otherMedia.genre.ordinal()) {
            return 1;
        }

        if (this.genre.ordinal() < otherMedia.genre.ordinal()) {
            return -1;
        }

        if (this.name.compareTo(otherMedia.name) > 0) {
            return 1;
        }

        if (this.name.compareTo(otherMedia.name) < 0) {
            return -1;
        }

        if (this.rating > otherMedia.rating) {
            return 1;
        }

        if (this.rating < otherMedia.rating) {
            return -1;
        }

        return 0;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.genre.hashCode();
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.rating;
        result = 31 * result + Double.hashCode(this.rentalPrice);
        return result;
    }

    /**
     * Returns the name of this media item.
     *
     * @return String representing the title of this media item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the rating of this media item.
     *
     * @return int representing the rating score of this media item
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Returns the rental price of this media item.
     *
     * @return double representing the rental price of this media item
     */
    public double getRentalPrice() {
        return this.rentalPrice;
    }
}
