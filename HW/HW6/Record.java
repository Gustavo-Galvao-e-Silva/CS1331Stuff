/**
 * Represents a music record with artist, title, duration, release date, play
 * count, and grade.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public abstract class Record {
    private String artist;
    private String title;
    private String duration;
    private String releaseDate;
    private int timesPlayed;
    private char grade;

    /**
     * Constructs a Record with the specified attributes.
     *
     * @param artist      the artist of the record
     * @param title       the title of the record
     * @param duration    the duration of the record
     * @param releaseDate the release date of the record
     * @param timesPlayed the number of times the record has been played
     * @param grade       the grade of the record; must be 'M', 'E', 'G', 'F', or
     *                    'P'
     * @throws IllegalArgumentException if artist, title, duration, or releaseDate
     *                                  is null or blank,
     *                                  if timesPlayed is negative, or if grade is
     *                                  not a valid value
     */
    public Record(
        String artist, String title, String duration, String releaseDate,
        int timesPlayed, char grade) throws IllegalArgumentException {
        if (artist == null || artist.isBlank()) {
            throw new IllegalArgumentException("Artist cannot be null or blank.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
        if (duration == null || duration.isBlank()) {
            throw new IllegalArgumentException("Duration cannot be null or blank.");
        }
        if (releaseDate == null || releaseDate.isBlank()) {
            throw new IllegalArgumentException("Release date cannot be null or blank.");
        }
        if (timesPlayed < 0) {
            throw new IllegalArgumentException("Times played cannot be negative.");
        }
        if (grade != 'M' && grade != 'E' && grade != 'G' && grade != 'F' && grade != 'P') {
            throw new IllegalArgumentException("Grade must be 'M', 'E', 'G', 'F', or 'P'.");
        }
        this.artist = artist.trim();
        this.title = title.trim();
        this.duration = duration.trim();
        this.releaseDate = releaseDate.trim();
        this.timesPlayed = timesPlayed;
        this.grade = grade;
    }

    /**
     * Returns the grade of this record.
     *
     * @return the grade
     */
    public char getGrade() {
        return grade;
    }

    /**
     * Sets the grade of this record.
     *
     * @param grade the new grade; must be 'M', 'E', 'G', 'F', or 'P'
     * @throws IllegalArgumentException if grade is not a valid value
     */
    public void setGrade(char grade) throws IllegalArgumentException {
        if (grade != 'M' && grade != 'E' && grade != 'G' && grade != 'F' && grade != 'P') {
            throw new IllegalArgumentException("Grade must be 'M', 'E', 'G', 'F', or 'P'.");
        }
        this.grade = grade;
    }

    /**
     * Increments the times played count by one.
     */
    public void incrementTimesPlayed() {
        timesPlayed++;
    }

    @Override
    public String toString() {
        return artist + "," + title + "," + duration + "," + releaseDate + "," + timesPlayed + "," + grade;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return timesPlayed == otherRecord.timesPlayed
            && grade == otherRecord.grade
            && artist.equals(otherRecord.artist)
            && title.equals(otherRecord.title)
            && duration.equals(otherRecord.duration)
            && releaseDate.equals(otherRecord.releaseDate);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + artist.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + duration.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + timesPlayed;
        result = 31 * result + grade;
        return result;
    }
}
