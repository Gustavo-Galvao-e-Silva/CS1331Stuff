/**
 * Car class for ride-share charging on travel and limited distances.
 *
 * @version 1.0.0
 * @author gsilva37
 * */
public class Car extends Vehicle {
    private double rate;
    private double fees;
    private int maxNumMiles;

    /**
     * Complete Car constructor.
     *
     * @param id String representing vehicle identitficator.
     * @param numMiles int representing how many miles the vehicle jas already travelled.
     * @param passengers string array representing passengers initially in the vehicle.
     * @param rate double representing travel cost rate in dollar/miles.
     * @param fees double representing flat cost added to all trips.
     * @param maxNumMiles int representing maximum distance car can travel.
    */
    public Car(String id, int numMiles, String[] passengers, double rate, double fees, int maxNumMiles) {
        super(id, numMiles, passengers);
        this.rate = rate;
        this.fees = fees;
        this.maxNumMiles = maxNumMiles;
    }

    /**
     * Simplified Car constructor, initializes passengers as an empty String array
     * of length 4, rate to 10.0 dollars / mile, and fees to 15 dollars.
     *
     * @param id String representing vehicle identitficator.
     * @param numMiles int representing how many miles the vehicle jas already travelled.
     * @param maxNumMiles int representing maximum distance car can travel.
    */
    public Car(String id, int numMiles, int maxNumMiles) {
        this(id, numMiles, new String[4], 10.0, 15.0, maxNumMiles);
    }

    /**
     * Simplified Car constructor, initializes numMiles to 0 miles, passengers as an empty String array
     * of length 4, rate to 10.0 dollars / mile, and fees to 15 dollars, and maxNumMiles to 200 miles.
     *
     * @param id String representing vehicle identitficator.
    */
    public Car(String id) {
        this(id, 0, 200);
    }

    @Override
    public boolean canDrive(int distance) {
        return distance >= 0 && this.numMiles + distance <= this.maxNumMiles;
    }

    @Override
    public double calculateCost(int distance) {
        if (!(this.canDrive(distance))) {
            return -1.0;
        }

        return distance * this.rate + this.fees;
    }

    @Override
    public boolean addPassengers(int distance, String[] newPassengers) {
        if (!(this.canDrive(distance)) || !(this.newPassengersFit(newPassengers))) {
            return false;
        }

        this.insertPassengers(newPassengers);
        this.chargeRide(distance);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Car)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Car otherCar = (Car) obj;
        return (super.equals((Vehicle) otherCar)
                && this.rate == otherCar.rate
                && this.fees == otherCar.fees
                && this.maxNumMiles == otherCar.maxNumMiles);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (super.hashCode());
        result = 31 * result + Double.hashCode(this.fees);
        result = 31 * result + Double.hashCode(this.rate);
        result = 31 * result + this.maxNumMiles;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Car %s It can only drive %d miles. It costs %.2f "
                + "dollars per mile and there is a one-time fee of %.2f dollars.",
                super.toString(), this.maxNumMiles, this.rate, this.fees);
    }
}
