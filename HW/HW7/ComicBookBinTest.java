public class ComicBookBinTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testMergeSortHeroes();
        testMergeHeroes();
        testHeroesOfAlliance();

        System.out.println("\n=== Results: " + passed + " passed, " + failed + " failed ===");
    }

    // -------------------------------------------------------------------------
    // mergeSortHeroes
    // -------------------------------------------------------------------------

    private static void testMergeSortHeroes() {
        // Already sorted
        assertSorted("Already sorted",
            mergeSortHeroes("Captain America", "Iron Man", "Spider-Man"),
            new String[]{"Captain America", "Iron Man", "Spider-Man"});

        // Reverse order
        assertSorted("Reverse order",
            mergeSortHeroes("Thor", "Spider-Man", "Iron Man", "Hulk", "Captain America"),
            new String[]{"Captain America", "Hulk", "Iron Man", "Spider-Man", "Thor"});

        // Single element
        assertSorted("Single element",
            mergeSortHeroes("Thor"),
            new String[]{"Thor"});

        // Two elements, already ordered
        assertSorted("Two elements ordered",
            mergeSortHeroes("Hulk", "Thor"),
            new String[]{"Hulk", "Thor"});

        // Two elements, reversed
        assertSorted("Two elements reversed",
            mergeSortHeroes("Thor", "Hulk"),
            new String[]{"Hulk", "Thor"});

        // Duplicates
        assertSorted("Duplicate names",
            mergeSortHeroes("Thor", "Thor", "Hulk"),
            new String[]{"Hulk", "Thor", "Thor"});

        // Example from spec
        assertSorted("Spec example",
            mergeSortHeroes("Spider-Man", "Iron Man", "Captain America", "Thor", "Hulk"),
            new String[]{"Captain America", "Hulk", "Iron Man", "Spider-Man", "Thor"});
    }

    // -------------------------------------------------------------------------
    // mergeHeroes
    // -------------------------------------------------------------------------

    private static void testMergeHeroes() {
        // Spec example
        Superhero[][] teams = {
            makeTeam("Iron Man", "Spider-Man"),
            makeTeam("Thor"),
            makeTeam("Captain America", "Hulk")
        };
        assertSorted("mergeHeroes spec example",
            ComicBookBin.mergeHeroes(teams),
            new String[]{"Captain America", "Hulk", "Iron Man", "Spider-Man", "Thor"});

        // Single team
        assertSorted("mergeHeroes single team",
            ComicBookBin.mergeHeroes(new Superhero[][]{makeTeam("Hulk", "Thor")}),
            new String[]{"Hulk", "Thor"});

        // All empty teams
        assertLength("mergeHeroes all empty teams",
            ComicBookBin.mergeHeroes(new Superhero[][]{new Superhero[0], new Superhero[0]}),
            0);

        // Empty array of teams
        assertLength("mergeHeroes no teams",
            ComicBookBin.mergeHeroes(new Superhero[0][]),
            0);

        // One empty, one non-empty
        Superhero[][] mixed = {new Superhero[0], makeTeam("Hulk", "Thor")};
        assertSorted("mergeHeroes one empty team",
            ComicBookBin.mergeHeroes(mixed),
            new String[]{"Hulk", "Thor"});
    }

    // -------------------------------------------------------------------------
    // heroesOfAlliance
    // -------------------------------------------------------------------------

    private static void testHeroesOfAlliance() {
        // Spec example (mixed, sorted input)
        Superhero[] mixed = {
            new Superhero("Captain America", Alliance.AVENGERS),
            new Superhero("Cyclops", Alliance.XMEN),
            new Superhero("Iron Man", Alliance.AVENGERS),
            new Superhero("Wolverine", Alliance.XMEN)
        };
        assertSorted("heroesOfAlliance AVENGERS",
            ComicBookBin.heroesOfAlliance(mixed, Alliance.AVENGERS),
            new String[]{"Captain America", "Iron Man"});

        assertSorted("heroesOfAlliance XMEN",
            ComicBookBin.heroesOfAlliance(mixed, Alliance.XMEN),
            new String[]{"Cyclops", "Wolverine"});

        // None match
        assertLength("heroesOfAlliance no match",
            ComicBookBin.heroesOfAlliance(mixed, Alliance.FANTASTIC_FOUR),
            0);

        // All match
        Superhero[] allAvengers = {
            new Superhero("Hawkeye", Alliance.AVENGERS),
            new Superhero("Iron Man", Alliance.AVENGERS),
            new Superhero("Thor", Alliance.AVENGERS)
        };
        assertSorted("heroesOfAlliance all match",
            ComicBookBin.heroesOfAlliance(allAvengers, Alliance.AVENGERS),
            new String[]{"Hawkeye", "Iron Man", "Thor"});

        // Empty input
        assertLength("heroesOfAlliance empty input",
            ComicBookBin.heroesOfAlliance(new Superhero[0], Alliance.AVENGERS),
            0);

        // Single match
        Superhero[] single = {new Superhero("Storm", Alliance.XMEN)};
        assertSorted("heroesOfAlliance single match",
            ComicBookBin.heroesOfAlliance(single, Alliance.XMEN),
            new String[]{"Storm"});

        // Single no match
        assertLength("heroesOfAlliance single no match",
            ComicBookBin.heroesOfAlliance(single, Alliance.AVENGERS),
            0);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private static Superhero[] mergeSortHeroes(String... names) {
        Superhero[] arr = new Superhero[names.length];
        for (int i = 0; i < names.length; i++) {
            arr[i] = new Superhero(names[i]);
        }
        return ComicBookBin.mergeSortHeroes(arr);
    }

    private static Superhero[] makeTeam(String... names) {
        Superhero[] arr = new Superhero[names.length];
        for (int i = 0; i < names.length; i++) {
            arr[i] = new Superhero(names[i]);
        }
        return arr;
    }

    private static void assertSorted(String label, Superhero[] result, String[] expected) {
        boolean ok = result.length == expected.length;
        if (ok) {
            for (int i = 0; i < expected.length; i++) {
                if (!result[i].toString().contains(expected[i])) {
                    ok = false;
                    break;
                }
            }
        }
        report(label, ok,
            ok ? "" : "expected " + join(expected) + " but got " + join(result));
    }

    private static void assertLength(String label, Superhero[] result, int expected) {
        boolean ok = result.length == expected;
        report(label, ok,
            ok ? "" : "expected length " + expected + " but got " + result.length);
    }

    private static void report(String label, boolean ok, String detail) {
        if (ok) {
            System.out.println("PASS: " + label);
            passed++;
        } else {
            System.out.println("FAIL: " + label + " — " + detail);
            failed++;
        }
    }

    private static String join(String[] arr) {
        String s = "[";
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
            if (i < arr.length - 1) s += ", ";
        }
        return s + "]";
    }

    private static String join(Superhero[] arr) {
        String s = "[";
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
            if (i < arr.length - 1) s += ", ";
        }
        return s + "]";
    }
}
