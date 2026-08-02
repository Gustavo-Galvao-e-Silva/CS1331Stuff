/**
 * Abstract class for traveling vehicle implementations.
 *
 * @version 1.0.0
 * @author gsilva37
 * */
public abstract class Vehicle {
    private final String id;
    private double earnings;
    protected int numMiles;
    protected String[] passengers;

    /**
     * Complete Vehicle constructor.
     *
     * @param id String representing vehicle identitficator.
     * @param numMiles int representing how many miles the vehicle jas already travelled.
     * @param passengers string array representing passengers initially in the vehicle.
    */
    public Vehicle(String id, int numMiles, String[] passengers) {
        this.id = id;
        this.numMiles = numMiles;
        this.passengers = passengers;
        this.earnings = 0.0;
    }

    /**
     * Simplified Vehicle constructor, initializes numMiles to 0 miles.
     *
     * @param id String representing vehicle identitficator.
     * @param passengers array of passengers initially in the vehicle.
    */
    public Vehicle(String id, String[] passengers) {
        this(id, 0, passengers);
    }

    /**
     * Determines if the vehicle can travel a given distance.
     *
     * @param distance int representing distance to be analyzed in miles.
     * @return boolean determining if vehicle can travel given distance.
     */
    public abstract boolean canDrive(int distance);

    /**
     * Calculate cost for vehivcle to travel given distance.
     *
     * @param distance int representing distance to be analyzed in miles.
     * @return double representing cost for the vehicle to travel that distance.
     */
    public abstract double calculateCost(int distance);

    /**
     * Adds new passengers from list to vehicle if distance and passenger conditions
     * are met.
     *
     * @param distance      int representing travel distance.
     * @param newPassengers String[] representing array of all new passengers.
     * @return boolean representing vehicle being able to fit new passengers and
     *         travel that distance.
     */
    public abstract boolean addPassengers(int distance, String[] newPassengers);

    /**
     * Updates vehicle's travelled distance and charges for all of them.
     *
     * @param distance int representing travel distance in miles.
     * @param numPassengers int representing number of passengers traveling.
    */
    public void chargeRide(int distance, int numPassengers) {
        if (this.canDrive(distance)) {
            this.numMiles += distance;
            this.earnings += numPassengers * calculateCost(distance);
        }
    }

    /**
     * Updates vhicle's travelled distance, charging for a single passenger.
     *
     * @param distance int representing travel distance in miles.
    */
    public void chargeRide(int distance) {
        this.chargeRide(distance, 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Vehicle)) {
            return false;
        }

        Vehicle otherVehicle = (Vehicle) obj;
        return (this.id.equals(otherVehicle.id) && this.numMiles == otherVehicle.numMiles);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(this.numMiles);
        result = 31 * result + this.id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s has travelled %d miles and has earned %.2f dollars.",
                this.id, this.numMiles, this.earnings);
    }

    protected int availableSeats() {
        int availableSeats = 0;
        for (String passenger : this.passengers) {
            if (passenger == null) {
                availableSeats++;
            }
        }

        return availableSeats;
    }

    protected boolean newPassengersFit(String[] newPassengers) {
        return availableSeats() >= newPassengers.length;
    }

    protected void insertPassengers(String[] newPassengers) {
        int newPassengerIndex = 0;
        for (int i = 0; i < this.passengers.length; i++) {
            if (this.passengers[i] == null && newPassengerIndex < newPassengers.length) {
                this.passengers[i] = newPassengers[newPassengerIndex];
                newPassengerIndex++;
            }
        }
    }
}
