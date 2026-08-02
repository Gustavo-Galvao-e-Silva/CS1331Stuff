/**
 * Represents an entity capable of admitting visitors.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public interface Admittable {

    /**
     * Admits the given array of visitors.
     *
     * @param visitors String[] representing the names of the visitors to admit.
     */
    void admit(String[] visitors);
}
