public class Alogrithms {
    public static void main(String[] args) {
        int[] testArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(numIsInList(9, testArray));
    }

    public static boolean numIsInList(int num, int[] list) {
        for (int i : list) {
            if (num == i) {
                return true;
            }
        }
        return false;
    }
}
