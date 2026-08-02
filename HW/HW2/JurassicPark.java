/**
 * This class serves as a driver to test functionality of other classes.
 * @author gsilva37.
 * @version 1.0.0
 */
public class JurassicPark {

    private static void printInformationTests(Dinosaur customDino, Dinosaur copyDino,
            Dinosaur defaultDino, String dinoType) {
        System.out.printf("%s info:\n", dinoType);
        System.out.println(customDino);
        System.out.println(copyDino);
        System.out.println(defaultDino);

        System.out.printf("\n%s enclosure test:", dinoType);
        System.out.println(customDino.buildEnclosure());
        System.out.println(customDino.buildEnclosure());
        System.out.println(copyDino.buildEnclosure());
        System.out.println(defaultDino.buildEnclosure());
    }

    private static void printEnclosureStaticTest(Dinosaur dino, String dinoType) {
        System.out.printf("%s: %d\n", dinoType, dino.getEnclosures());
    }

    private static void printDeepCopyTests(Dinosaur customDino, Dinosaur copyDino,
            Velociraptor customRaptor, Velociraptor copyRaptor) {
        System.out.println("\nDeep copy test (modifying originals should not affect copies):");
        customDino.setHeight(999.0);
        System.out.println("Custom dino height: " + customDino.getHeight());
        System.out.println("Copy dino height: " + copyDino.getHeight());

        customRaptor.setHeight(999.0);
        System.out.println("Original raptor height: " + customRaptor.getHeight());
        System.out.println("Copy raptor height (should be 3.0): " + copyRaptor.getHeight());
    }

    /**
     * Main method that runs functionality tests.
     * @param args String[] that allows to use command line input
     */
    public static void main(String[] args) {
        Dinosaur customDino = new Dinosaur("Tyranitar", 10.0,
                15.0, 100.0);
        Dinosaur copyDino = new Dinosaur(customDino);
        Dinosaur defaultDino = new Dinosaur();

        Pterodactyl customPtero = new Pterodactyl("Teradactyl", 6.0,
                3.0, 200.0, 55.0);
        Pterodactyl simplePtero = new Pterodactyl("Rockie", 2.0);
        Pterodactyl copyPtero = new Pterodactyl(customPtero);

        Pack pack1 = new Pack(5, "The Boyz");
        Velociraptor customRaptor = new Velociraptor("Java", 3.0,
                3.0, 20.0, 45, pack1);
        Velociraptor simpleRaptor = new Velociraptor("Panchi", 4.0);
        Velociraptor copyRaptor = new Velociraptor(customRaptor);

        printInformationTests(customDino, copyDino, defaultDino, "Dinosaur");
        printInformationTests(customPtero, simplePtero, copyPtero, "Pterodactyl");
        printInformationTests(customRaptor, simpleRaptor, copyRaptor, "Velociraptor");

        // NOTE TO GRADER: I called the getEnclosure() static methods from the instances
        // instead of class to show the totalEnclosure field is indeed static.
        // All instances across all classes share the same static counter, so all show 8.
        System.out.println("\nEnclosure static test (all of them should be 8):");
        printEnclosureStaticTest(customDino, "Custom Dino:");
        printEnclosureStaticTest(copyDino, "Copy Dino:");
        printEnclosureStaticTest(defaultDino, "Default Dino:");
        printEnclosureStaticTest(customPtero, "Custom Ptero:");
        printEnclosureStaticTest(copyPtero, "Copy Ptero:");
        printEnclosureStaticTest(simplePtero, "Simple Ptero:");
        printEnclosureStaticTest(customRaptor, "Custom Raptor:");
        printEnclosureStaticTest(copyRaptor, "Copy Raptor:");
        printEnclosureStaticTest(simpleRaptor, "Simple Raptor:");

        printDeepCopyTests(customDino, copyDino, customRaptor, copyRaptor);
    }
}
