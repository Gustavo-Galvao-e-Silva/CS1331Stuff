/**
 * Pack represents a set of Dinosaur objects living together. Its fields are
 * constant, never modifying after construction.
 * @author gsilva37.
 * @version 1.0.0
 */
public class Pack {
    public static final int DEFAULT_PACK_SIZE = 4;

    private final int size;
    private final String packName;

    /**
     * Pack constructor, requires all information about pack.
     * @param size int representing number of dinosaurs in the pack. Defaults to 4
     *             if negative number is given
     * @param name String representing pack name. Defaults to "The Power Pack" if
     *             invalid name is given
     */
    public Pack(int size, String name) {
        this.size = size < 0 ? DEFAULT_PACK_SIZE : size;
        this.packName = (name == null || name.isBlank()) ? "The Power Pack" : name;
    }

    @Override
    public String toString() {
        return String.format("%s is a family of dinosaurs of size %d!", this.packName, this.size);
    }

    /**
     * Getter for Pack's size.
     * @return int representing the Pack's number of dinosaurs
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Getter for Pack's name.
     * @return String representing the Pack's name
     */
    public String getName() {
        return this.packName;
    }
}
