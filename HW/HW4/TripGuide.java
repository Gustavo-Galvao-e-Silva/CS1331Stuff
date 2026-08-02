/**
 * Driver class for testing the Attraction and RollerCoaster implementations.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class TripGuide {

    /**
     * Tests attraction admission, rating, visitor printing, comparison, and edge
     * cases.
     *
     * @param args String[] command-line arguments
     */
    public static void main(String[] args) {
        Attraction[] attractions = {
            new Attraction("Haunted Mansion", 8.50),
            new Attraction("Ghost Train", 8.50),
            new RollerCoaster("Thunder Run", 8.50, 30),
            new Attraction("Ferris Wheel", 12.00),
            new RollerCoaster("Sky Rocket", 15.00, 50),
            new Attraction("Bumper Cars", 6.00),
            new RollerCoaster("Vortex", 6.00, 25)
        };

        String[] group1 = {"Alice", "Bob", "Carol", "Dave"};
        String[] group2 = {"Eve", "Frank", "Grace", "Heidi", "Ivan"};
        String[] group3 = {"Jack", "Kara", "Leo", "Mia", "Noah", "Olivia",
            "Paul", "Quinn", "Rose", "Sam", "Tina", "Uma"};

        for (Attraction a : attractions) {
            a.admit(group1);
            a.admit(group2);
            a.admit(group3);
            a.printVisitors();
        }

        attractions[0].rateAndExit(0, 8);
        attractions[1].rateAndExit(0, 8);

        attractions[3].rateAndExit(0, 9);
        attractions[5].rateAndExit(1, 4);

        for (Attraction a : attractions) {
            System.out.println(a);
            a.printVisitors();
        }

        System.out.println(attractions[0].compareTo(attractions[3]));
        System.out.println(attractions[0].compareTo(attractions[1]));
        System.out.println(attractions[0].compareTo(attractions[5]));
        System.out.println(attractions[0].compareTo(null));

        attractions[0].admit(new String[] {});

        String[] bigGroup = new String[30];
        for (int i = 0; i < 30; i++) {
            bigGroup[i] = "Person" + i;
        }
        attractions[6].admit(bigGroup);

        System.out.println(new Attraction(null, 5.00));
        System.out.println(new Attraction("   ", 5.00));
        System.out.println(new Attraction("Test", -10.00));
    }
}
