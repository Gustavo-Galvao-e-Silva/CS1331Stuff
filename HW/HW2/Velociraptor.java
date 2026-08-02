/**
 * Velociraptor class extends Dinosaur class with speed and pack behavior.
 * @author gsilva37
 * @version 1.0.0
 */
public class Velociraptor extends Dinosaur {
    public static final int DEFAULT_RAPTOR_SPEED = 30;

    private int speed;
    private Pack pack;

    /**
     * Complete Velociraptor constructor requiring all Velociraptor information.
     * @param name   String representing name of the Velociraptor
     * @param height double representing Velociraptor height
     * @param width  double representing Velociraptor width
     * @param weight double representing Velociraptor weight
     * @param speed  int representing Velociraptor speed. Defaults to 30 if negative
     * @param pack   Pack representing the pack this Velociraptor belongs to
     */
    public Velociraptor(String name, double height, double width, double weight, int speed, Pack pack) {
        super(name, height, width, weight);
        this.speed = speed < 0 ? DEFAULT_RAPTOR_SPEED : speed;
        this.pack = pack; // We can shallow copy this as pack is immutable
    }

    /**
     * Simple Velociraptor constructor requiring only name and height, using default
     * values for all other fields: width = 20.0, weight = 1000.0, speed = 30,
     * pack = null.
     * @param name   String representing name of the Velociraptor
     * @param height double representing Velociraptor height
     */
    public Velociraptor(String name, double height) {
        this(name, height, DEFAULT_DINO_WIDTH, DEFAULT_DINO_WEIGHT, DEFAULT_RAPTOR_SPEED, null);
    }

    /**
     * Deep copy Velociraptor constructor.
     * @param otherVelociraptor Velociraptor object to be copied
     */
    public Velociraptor(Velociraptor otherVelociraptor) {
        super(otherVelociraptor);
        this.speed = otherVelociraptor.speed;
        this.pack = otherVelociraptor.pack;
    }

    @Override
    public double enclosureSize() {
        int multiplier = this.pack == null ? 4 : this.pack.getSize();
        return multiplier * this.getWidth() * this.getHeight();
    }

    @Override
    public double calculateFood() {
        return this.getWeight() * this.speed * this.getHeight();
    }

    @Override
    public String toString() {
        return this.pack == null ? super.toString() : String.format("%s %s", this.pack.toString(), super.toString());
    }

    /**
     * Getter for Velociraptor's speed.
     * @return int representing the Velociraptor's speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Getter for Velociraptor's pack.
     * @return Pack representing the pack this Velociraptor belongs to, or null if
     *         it has no pack
     */
    public Pack getPack() {
        return this.pack;
    }
}
