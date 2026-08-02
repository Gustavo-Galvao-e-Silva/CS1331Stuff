/**
 * Represents a roller coaster attraction with a maximum visitor capacity.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class RollerCoaster extends Attraction {
    private final int maxCapacity;
    private int occupancy = 0;

    /**
     * Constructs a RollerCoaster with the given name, admission fee, and maximum
     * capacity, which
     * defaults to 25 if less than that.
     *
     * @param name         String representing the name of the roller coaster.
     * @param admissionFee double representing the admission fee in dollars.
     * @param maxCapacity  int representing the maximum number of visitors allowed
     *                     at once.
     */
    public RollerCoaster(String name, double admissionFee, int maxCapacity) {
        super(name, admissionFee);
        this.maxCapacity = maxCapacity >= 25 ? maxCapacity : 25;
    }

    /**
     * Constructs a RollerCoaster with the given name, a default admission fee of
     * 5.25 dollars,
     * and a maximum capacity of 25.
     *
     * @param name String representing the name of the roller coaster.
     */
    public RollerCoaster(String name) {
        super(name);
        this.maxCapacity = 25;
    }

    @Override
    public void admit(String[] visitors) {
        if (this.occupancy + visitors.length > this.maxCapacity) {
            System.out.println("RollerCoaster has reached maximum capacity. Please visit another time!");
            return;
        }

        this.occupancy += visitors.length;
        super.admit(visitors);
    }

    @Override
    public int rateAndExit(int groupIndex, int rating) {
        int numVisitorsLeft = super.rateAndExit(groupIndex, rating);
        this.occupancy -= numVisitorsLeft;
        return numVisitorsLeft;
    }

    /**
     * Returns the percentage of maximum capacity currently occupied.
     *
     * @return double representing the percent occupancy.
     */
    public double percentOccupancy() {
        return super.roundToTwoDecimals((double) this.occupancy / this.maxCapacity) * 100;
    }

    @Override
    public String toString() {
        return String.format("RollerCoaster: %s/%.2f", super.toString(), this.percentOccupancy()) + "%";
    }
}
