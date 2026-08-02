/**
 * Base Dinosaur class for other classes, allows enclosure and food operations.
 * @author gsilva37
 * @version 1.0.0
 */
public class Dinosaur {
    public static final String DEFAULT_DINO_NAME = "Barney";
    public static final double DEFAULT_DINO_HEIGHT = 15.0;
    public static final double DEFAULT_DINO_WIDTH = 20.0;
    public static final double DEFAULT_DINO_WEIGHT = 1000.0;

    protected final String name;
    private double weight;
    private double width;
    private double height;
    protected static int totalEnclosures = 0;

    /**
     * Complete Dinosaur constructor requiring all Dinosaur information.
     * @param name   String representing name of the Dinosaur, defaults to "Barney" if unvalid
     * @param height Double representing Dinosaur height
     * @param width  Double representing Dinosaur width
     * @param weight Double representing Dinosaur weight
     */
    public Dinosaur(String name, double height, double width, double weight) {
        this.name = (name == null || name.isBlank()) ? DEFAULT_DINO_NAME : name;
        this.height = height;
        this.width = width;
        this.weight = weight;
    }

    /**
     * Default Dinosaur constructor requiring no Dinosaur information.
     */
    public Dinosaur() {
        this("", DEFAULT_DINO_HEIGHT, DEFAULT_DINO_WIDTH, DEFAULT_DINO_WEIGHT);
    }

    /**
     * Deep copy Dinosaur constructor.
     * @param otherDino Dinosaur object to be copied
     */
    public Dinosaur(Dinosaur otherDino) {
        this(otherDino.name, otherDino.height, otherDino.width, otherDino.weight);
    }

    /**
     * Calculates Dinosaur's enclosure size.
     * @return Double representing enclosure size in square feet.
     */
    public double enclosureSize() {
        return 10 * this.width * this.height;
    }

    /**
     * Calculates Dinosaur's food intake in pounds.
     * @return Double representing food amount
     */
    public double calculateFood() {
        return this.weight * this.width * this.height;
    }

    /**
     * Generates String representation of Dinosaur object.
     * @return String representation of object
     */
    @Override
    public String toString() {
        return String.format("%s requires a %.2f square foot enclosure and %.2f pounds of food.", this.name,
                this.enclosureSize(), this.calculateFood());
    }

    /**
     * Builds Dinosaur enclosure if enclosure size and food intake permit.
     * @return String representing successfull or unsuccessful enclosure building
     */
    public String buildEnclosure() {
        if (this.enclosureSize() > 6000.0 || this.calculateFood() > 80000.0) {
            return String.format("%s %s is too expensive for the park!", this, this.name);
        }

        totalEnclosures++;
        return String.format("%s %s has been added to the park!", this, this.name);
    }

    /**
     * Getter for Dinosaur's name.
     * @return String representing the Dinosaur's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for Dinosaur's weight.
     * @return double representing the Dinosaur's weight
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Getter for Dinosaur's width.
     * @return double representing the Dinosaur's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for Dinosaur's height.
     * @return double representing the Dinosaur's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getter for number of dinosaurs in enclosures.
     * @return int representing Dinosaur class totalEnclosures
     */
    public static int getEnclosures() {
        return totalEnclosures;
    }

    /**
     * Setter for Dinosaur's height.
     * @param newHeight double for new Dinosaur height
     */
    public void setHeight(double newHeight) {
        this.height = newHeight;
    }
}
