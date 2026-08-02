/**
 * Driver class for testing Car and Bus classes.
 *
 * @version 1.0.0
 * @author gsilva37
 * */
public class Driver {
    /**
     * Method to run Car and Bus logic tests.
     *
     * @param args String[] representing command-line arguments for running Driver class.
    */
    public static void main(String[] args) {
        String[] passengerCar1 = {null, "Milena", null, null};
        Car car1 = new Car("Car1", 0, passengerCar1, 2.0, 5.0, 50);
        Car car2 = new Car("Car2");
        Car car2Equal = new Car("Car2");

        System.out.println(car2.equals(car2Equal));

        System.out.println(car1.toString());
        System.out.println(car2.toString());

        String[] newPassengers = {"Eduardo", "Mariana", "Ricardo", "Lusca"};

        car1.addPassengers(20, newPassengers);
        car2.addPassengers(10, newPassengers);

        System.out.println(car1);
        System.out.println(car2);

        System.out.println(car1.equals(car2));

        Bus bus1 = new Bus("Bus1", 0, "Piedmont", 2);
        Bus bus2 = new Bus("Bus2", "Clough");

        System.out.println(bus1.toString());
        System.out.println(bus2.toString());

        System.out.println(bus1.toString());
        System.out.println(bus2.toString());

        bus1.addPassengers(10, newPassengers);
        bus2.addPassengers(2000, newPassengers);

        System.out.println(bus1);
        System.out.println(bus2);

        System.out.println(bus1.equals(bus2));

        String[] moreNewPassengers = {
            "Olivia", "Liam", "Sophia", "Noah",
            "Emma", "Oliver", "Ava", "Elijah",
            "Charlotte", "James", "Amelia", "Benjamin",
            "Mia", "Lucas", "Harper", "Henry",
            "Evelyn", "Alexander"
        };

        bus1.addPassengers(150, moreNewPassengers);

        System.out.println(bus1.toString());
    }
}
