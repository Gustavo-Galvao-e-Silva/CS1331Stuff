/**
 * Contains recursive methods for sorting, filtering, and processing superhero arrays.
 *
 * @author gustavogalvao
 * @version 1.0.0
 */
public class ComicBookBin {
    /**
     * Sorts the given array of superheroes in ascending order by name using merge sort.
     *
     * @param heroes Superhero[] to be sorted.
     * @return Superhero[] sorted in ascending order.
     */
    public static Superhero[] mergeSortHeroes(Superhero[] heroes) {
        int length = heroes.length;
        if (length <= 1) {
            return heroes;
        }

        int middle = length / 2;
        Superhero[] leftHeroes = RecursionUtils.copyOfRange(heroes, 0, middle);
        Superhero[] rightHeroes = RecursionUtils.copyOfRange(heroes, middle, length);

        Superhero[] leftHeroesSorted = mergeSortHeroes(leftHeroes);
        Superhero[] rightHeroesSorted = mergeSortHeroes(rightHeroes);

        return RecursionUtils.merge(leftHeroesSorted, rightHeroesSorted);
    }

    /**
     * Flattens 2D array of superheroes into sorted 1D array.
     *
     * @param heroes 2D array of sorted superhero sub-teams.
     * @return Superhero[] containing all heroes from every sub-team in sorted order.
     */
    public static Superhero[] mergeHeroes(Superhero[][] heroes) {
        return mergeHeroesHelper(heroes, 0);
    }

    /**
     * Returns only the superheroes belonging to the specified alliance.
     *
     * @param heroes   a sorted array of superheroes.
     * @param alliance the alliance to filter by.
     * @return Superhero[] containing only the heroes of the specified alliance.
     */
    public static Superhero[] heroesOfAlliance(Superhero[] heroes, Alliance alliance) {
        Superhero[] result = new Superhero[countAlliance(heroes, alliance, 0)];
        fillAlliance(heroes, alliance, result, 0, 0);
        return result;
    }

    /**
     * Computes the total strength of all superheroes in the given array.
     *
     * @param heroes array of superheroes.
     * @return sum of the strength values of all heroes in the array
     */
    public static double totalStrength(Superhero[] heroes) {
        if (heroes.length == 0) {
            return 0;
        }

        return heroes[0].getAlliance().getStrength()
            + totalStrength(RecursionUtils.copyOfRange(heroes, 1, heroes.length));
    }

    /**
     * Reverses the given array of superheroes in-place.
     *
     * @param heroes a sorted array of superheroes to reverse in-place.
     */
    public static void flip(Superhero[] heroes) {
        flipHelper(heroes, 0, heroes.length - 1);
    }

    private static void flipHelper(Superhero[] heroes, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        Superhero temp = heroes[leftIndex];
        heroes[leftIndex] = heroes[rightIndex];
        heroes[rightIndex] = temp;

        flipHelper(heroes, leftIndex + 1, rightIndex - 1);
    }

    private static Superhero[] mergeHeroesHelper(Superhero[][] heroes, int index) {
        if (index >= heroes.length) {
            return new Superhero[0];
        }

        return RecursionUtils.merge(heroes[index], mergeHeroesHelper(heroes, index + 1));
    }

    private static int countAlliance(Superhero[] heroes, Alliance alliance, int index) {
        if (index >= heroes.length) {
            return 0;
        }

        int rest = countAlliance(heroes, alliance, index + 1);
        return heroes[index].getAlliance().equals(alliance) ? rest + 1 : rest;
    }

    private static void fillAlliance(Superhero[] heroes, Alliance alliance,
        Superhero[] result, int sourceIndex, int destinationIndex) {
        if (sourceIndex >= heroes.length) {
            return;
        }

        if (heroes[sourceIndex].getAlliance().equals(alliance)) {
            result[destinationIndex] = heroes[sourceIndex];
            fillAlliance(heroes, alliance, result, sourceIndex + 1, destinationIndex + 1);
        } else {
            fillAlliance(heroes, alliance, result, sourceIndex + 1, destinationIndex);
        }
    }
}
