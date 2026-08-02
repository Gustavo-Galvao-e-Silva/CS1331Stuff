/**
 * Pterodactyl class extends the Dinosaur class while adding flying capabilities.
 * @author gsilva37
 * @version 1.0.0
 */
public class Pterodactyl extends Dinosaur {
    public static final double DEFAULT_PTERO_FLIGHT_CEILING = 50.0;
    public static final double DEFAULT_PTERO_WIDTH = 12.0;
    private double flightCeiling;

    /**
     * Complete Pterodactyl constructor requiring all Pterodactyl information and
     * returning fully custom Pterodactyl object.
     * @param name   String representing name of the Pterodactyl
     * @param height double representing Pterodactyl height
     * @param width  double representing Pterodactyl width
     * @param weight double representing Pterodactyl weight
     * @param flightCeiling double representing maximum Pterodactyl flight altitude
     */
    public Pterodactyl(String name, double height, double width, double weight, double flightCeiling) {
        super(name, height, width, weight);
        this.flightCeiling = (flightCeiling < 10.0 || flightCeiling > 100.0) ? 50.0 : flightCeiling;
    }

    /**
     * Simple Pterodactyl constructor requiring only name and width, using default
     * values: height = 15.0, weight = 1000.0, flightCeiling = 50.0.
     * @param name  String representing name of the Pterodactyl
     * @param width double representing Pterodactyl width
     */
    public Pterodactyl(String name, double width) {
        this(name, DEFAULT_DINO_HEIGHT, width, DEFAULT_DINO_WEIGHT, DEFAULT_PTERO_FLIGHT_CEILING);
    }

    /**
     * Minimal Pterodactyl constructor requiring only a name, using default
     * values: height = 15.0, width = 12.0, weight = 1000.0, flightCeiling = 50.0.
     * @param name String representing name of the Pterodactyl
     */
    public Pterodactyl(String name) {
        this(name, DEFAULT_PTERO_WIDTH);
    }

    /**
     * Deep copy Pterodactyl constructor.
     * @param otherPterodactyl Pterodactyl object to be copied
     */
    public Pterodactyl(Pterodactyl otherPterodactyl) {
        super(otherPterodactyl);
        this.flightCeiling = otherPterodactyl.flightCeiling;
    }

    @Override
    public double enclosureSize() {
        return 4 * (this.getWidth() * this.getHeight()) + this.flightCeiling;
    }

    @Override
    public String toString() {
        return String.format("%s can fly %.2f feet into the air! %s", this.name, this.flightCeiling, super.toString());
    }

    /**
     * Getter for Pterodactyl's flight ceiling.
     * @return double representing the maximum height the Pterodactyl can fly
     */
    public double getFlightCeiling() {
        return this.flightCeiling;
    }
}
