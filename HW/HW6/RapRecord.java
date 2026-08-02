/**
 * Represents a rap music record with an additional producer field.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class RapRecord extends Record {
    private String producer;

    /**
     * Constructs a RapRecord with the specified attributes.
     *
     * @param artist      the artist of the record
     * @param title       the title of the record
     * @param duration    the duration of the record
     * @param releaseDate the release date of the record
     * @param timesPlayed the number of times the record has been played
     * @param grade       the grade of the record; must be 'M', 'E', 'G', 'F', or
     *                    'P'
     * @param producer    the producer of the record
     * @throws IllegalArgumentException if any string field is null or blank,
     *                                  if timesPlayed is negative, or if grade is
     *                                  not a valid value
     */
    public RapRecord(
        String artist, String title, String duration, String releaseDate,
        int timesPlayed, char grade, String producer) throws IllegalArgumentException {
        super(artist, title, duration, releaseDate, timesPlayed, grade);
        if (producer == null || producer.isBlank()) {
            throw new IllegalArgumentException("Producer cannot be null or blank.");
        }
        this.producer = producer.trim();
    }

    /**
     * Returns the producer of this record.
     *
     * @return the producer
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Sets the producer of this record.
     *
     * @param producer the new producer
     * @throws IllegalArgumentException if producer is null or blank
     */
    public void setProducer(String producer) throws IllegalArgumentException {
        if (producer == null || producer.isBlank()) {
            throw new IllegalArgumentException("Producer cannot be null or blank.");
        }
        this.producer = producer.trim();
    }

    @Override
    public String toString() {
        return String.format("RapRecord,%s,%s", super.toString(), this.producer);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof RapRecord)) {
            return false;
        }

        RapRecord otherRapRecord = (RapRecord) other;
        return super.equals(otherRapRecord) && this.producer.equals(otherRapRecord.producer);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + producer.hashCode();
        return result;
    }
}
