/**
 * Represents a video game available for rental, extending Media with
 * player count and console requirement fields.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class VideoGame extends Media {
    private int maxPlayers;
    private boolean needsConsole;

    /**
     * Constructs a VideoGame with all fields specified.
     *
     * @param genre        Genre representing the genre of the video game
     * @param name         String representing the title of the video game
     * @param rating       int representing the rating score of the video game
     * @param rentalPrice  double representing the price to rent the video game
     * @param maxPlayers   int representing the maximum number of players supported
     * @param needsConsole boolean representing whether the game requires a console
     *                     to play
     */
    public VideoGame(Genre genre, String name, int rating, double rentalPrice, int maxPlayers, boolean needsConsole) {
        super(genre, name, rating, rentalPrice);
        this.maxPlayers = maxPlayers;
        this.needsConsole = needsConsole;
    }

    /**
     * Constructs a VideoGame with a default rental price of $5.00, max players of
     * 2, and no console requirement.
     *
     * @param genre  Genre representing the genre of the video game
     * @param name   String representing the title of the video game
     * @param rating int representing the rating score of the video game
     */
    public VideoGame(Genre genre, String name, int rating) {
        this(genre, name, rating, 5.0, 2, false);
    }

    @Override
    public String toString() {
        String needsConsoleString = this.needsConsole ? "does" : "does not";
        return String.format("%s, Players: %d, %s need a console", super.toString(), this.maxPlayers,
                needsConsoleString);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || !(other instanceof VideoGame)) {
            return false;
        }

        VideoGame otherVideoGame = (VideoGame) other;
        return super.equals(otherVideoGame)
            && this.maxPlayers == otherVideoGame.maxPlayers
            && this.needsConsole == otherVideoGame.needsConsole;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + this.maxPlayers;
        result = 31 * result + (this.needsConsole ? 1 : 0);
        return result;
    }

    /**
     * Returns whether this video game requires a console to play.
     *
     * @return boolean representing whether a console is required to play
     */
    public boolean getNeedsConsole() {
        return this.needsConsole;
    }
}
