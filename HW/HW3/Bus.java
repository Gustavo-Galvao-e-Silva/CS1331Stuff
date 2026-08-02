/**
 * Bus class for ticket-style charging on travel and unlimited distances.
 *
 * @version 1.0.0
 * @author gsilva37
 * */
public class Bus extends Vehicle {
    private String location;
    private int stopsPerMile;

    /**
     * Complete Bus constructor, initializes passengers to an empty 20 string array.
     *
     * @param id String representing vehicle identitficator.
     * @param numMiles int representing how many miles the vehicle jas already travelled.
     * @param location String representing location bus operates in.
     * @param stopsPerMile int representing how many stops per mile travelled the bus takes on average.
    */
    public Bus(String id, int numMiles, String location, int stopsPerMile) {
        super(id, numMiles, new String[20]);
        this.location = location;
        this.stopsPerMile = stopsPerMile;
    }

    /**
     * Simplified Bus constructor, initializes numMiles to 0 miles and stopsPerMile
     * to 2 stops / mile.
     *
     * @param id String representing vehicle identitficator.
     * @param location String representing location bus operates in.
    */
    public Bus(String id, String location) {
        this(id, 0, location, 2);
    }

    @Override
    public boolean canDrive(int distance) {
        return distance >= 0;
    }

    @Override
    public double calculateCost(int distance) {
        if (!(this.canDrive(distance))) {
            return -1.0;
        }

        return 3.0 * distance / this.stopsPerMile;
    }

    @Override
    public boolean addPassengers(int distance, String[] newPassengers) {
        if (!(canDrive(distance))) {
            return false;
        }
        int numBoardedPassengers = min(this.availableSeats(), newPassengers.length);
        this.insertPassengers(newPassengers);
        chargeRide(distance, numBoardedPassengers);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Bus)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Bus otherBus = (Bus) obj;
        return (super.equals((Vehicle) otherBus)
                && this.stopsPerMile == otherBus.stopsPerMile
                && this.location.equals(otherBus.location));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + this.location.hashCode();
        result = 31 * result + this.stopsPerMile;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Bus %s This bus drives around %s and makes %d stops per mile.",
                super.toString(), this.location, this.stopsPerMile);
    }

    private static int min(int a, int b) {
        return a <= b ? a : b;
    }
}
