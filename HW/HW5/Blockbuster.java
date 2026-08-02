import java.util.ArrayList;

/**
 * Represents a Blockbuster store that manages an inventory of Media items.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class Blockbuster {
    private ArrayList<Media> inventory;

    /**
     * Constructs an empty Blockbuster with no media in inventory.
     */
    public Blockbuster() {
        this.inventory = new ArrayList<Media>();
    }

    /**
     * Adds a media item to the inventory.
     *
     * @param media Media representing the media item to add
     */
    public void addMedia(Media media) {
        this.inventory.add(media);
    }

    /**
     * Removes the first media item from the inventory that equals the given media.
     *
     * @param media Media representing the media item to remove
     * @return Media representing the removed media item, or null if not found
     */
    public Media removeMedia(Media media) {
        Media removedMedia = null;
        for (int i = 0; i < this.inventory.size(); i++) {
            Media m = this.inventory.get(i);
            if (media.equals(m)) {
                removedMedia = m;
                this.inventory.remove(i);
                break;
            }
        }
        return removedMedia;
    }

    /**
     * Sorts the inventory using bubble sort ordered by the increasing ordering.
     */
    public void sortMedia() {
        for (int i = 0; i < this.inventory.size() - 1; i++) {
            for (int j = 0; j < this.inventory.size() - 1 - i; j++) {
                Media currMedia = this.inventory.get(j);
                Media nextMedia = this.inventory.get(j + 1);
                if (currMedia.compareTo(nextMedia) > 0) {
                    swapArrayItems(j, j + 1, this.inventory);
                }
            }
        }
    }

    /**
     * Searches the sorted inventory for a media item using binary search.
     *
     * @param media Media representing the media item to search for
     * @return Media representing matching item from the inventory, or null if not
     *         found
     */
    public Media findMedia(Media media) {
        int left = 0;
        int right = this.inventory.size() - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            Media middleMedia = this.inventory.get(middle);
            int comparisonValue = middleMedia.compareTo(media);
            if (comparisonValue == 0) {
                return middleMedia;
            }

            if (comparisonValue > 0) {
                right = middle - 1;
            }

            if (comparisonValue < 0) {
                left = middle + 1;
            }
        }
        return null;
    }

    /**
     * Returns the most popular movie in the inventory, determined by highest rating
     * and then lexicographically smallest movie name.
     *
     * @return Movie representing the most popular movie, or null if no movies are
     *         in inventory
     */
    public Movie getMostPopularMovie() {
        Movie mostPopularMovie = null;
        for (Media media : this.inventory) {
            if (media instanceof Movie) {
                Movie movie = (Movie) media;
                if (mostPopularMovie == null) {
                    mostPopularMovie = movie;
                } else {
                    mostPopularMovie = morePopularMovie(mostPopularMovie, movie);
                }
            }
        }
        return mostPopularMovie;
    }

    private static void swapArrayItems(int i, int j, ArrayList<Media> list) {
        Media temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private Movie morePopularMovie(Movie movieA, Movie movieB) {
        int movieARating = movieA.getRating();
        String movieAName = movieA.getName();
        int movieBRating = movieB.getRating();
        String movieBName = movieB.getName();
        if (movieARating == movieBRating) {
            return movieAName.compareTo(movieBName) < 0 ? movieA : movieB;
        }

        return movieARating > movieBRating ? movieA : movieB;
    }
}
