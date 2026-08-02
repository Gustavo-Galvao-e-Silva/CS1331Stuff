/**
 * Represents a jazz music record with an additional audio engineer field.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class JazzRecord extends Record {
    private String audioEngineer;

    /**
     * Constructs a JazzRecord with the specified attributes.
     *
     * @param artist        the artist of the record
     * @param title         the title of the record
     * @param duration      the duration of the record
     * @param releaseDate   the release date of the record
     * @param timesPlayed   the number of times the record has been played
     * @param grade         the grade of the record; must be 'M', 'E', 'G', 'F', or
     *                      'P'
     * @param audioEngineer the audio engineer of the record
     * @throws IllegalArgumentException if any string field is null or blank,
     *                                  if timesPlayed is negative, or if grade is
     *                                  not a valid value
     */
    public JazzRecord(
        String artist, String title, String duration, String releaseDate,
        int timesPlayed, char grade, String audioEngineer) throws IllegalArgumentException {
        super(artist, title, duration, releaseDate, timesPlayed, grade);
        if (audioEngineer == null || audioEngineer.isBlank()) {
            throw new IllegalArgumentException("Audio engineer cannot be null or blank.");
        }
        this.audioEngineer = audioEngineer.trim();
    }

    @Override
    public String toString() {
        return String.format("JazzRecord,%s,%s", super.toString(), this.audioEngineer);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof JazzRecord)) {
            return false;
        }

        JazzRecord otherJazzRecord = (JazzRecord) other;
        return super.equals(otherJazzRecord) && this.audioEngineer.equals(otherJazzRecord.audioEngineer);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + audioEngineer.hashCode();
        return result;
    }
}
