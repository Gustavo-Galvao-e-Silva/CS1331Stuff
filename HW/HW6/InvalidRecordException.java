/**
 * Checked exception thrown when encountering an invalid record.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class InvalidRecordException extends Exception {
    /**
     * Constructs an InvalidRecordException with the specified message.
     *
     * @param message the detail message
     */
    public InvalidRecordException(String message) {
        super(message);
    }

    /**
     * Constructs an InvalidRecordException with the default message "Invalid Record".
     */
    public InvalidRecordException() {
        this("Invalid Record");
    }
}
