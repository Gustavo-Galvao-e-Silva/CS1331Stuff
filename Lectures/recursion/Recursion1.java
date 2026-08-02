import java.util.Arrays;

public class Recursion1 {
    public static <T extends Comparable<T>> T[] mergeSort(T[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        int middle = arr.length / 2;
        T[] firstHalf = getFirstHalf(arr);
        T[] secondHalf = getSecondHalf(arr);

        T[] sortedFirstHalf = mergeSort(firstHalf);
        T[] sortedSecondHalf = mergeSort(secondHalf);

        fo
    }

    private static <T extends Comparable<T>> T[] getFirstHalf(T[] arr) {
        int middle = arr.length / 2;
        return Arrays.copyOfRange(arr, 0, middle);
    }

    private static <T extends Comparable<T>> T[] getSecondHalf(T[] arr) {
        int middle = arr.length / 2;
        return Arrays.copyOfRange(arr, middle, arr.length);
    }

    private static <T extends Comparable<T>> T[] mergeSortedArrays(T[] arr1, T[] arr2) {
        int resultSize = arr1.length + arr2.length;
        T[] result = (T[]) new Object[resultSize];
        int i1, i2 = 0;
        while (i1 + i2 < resultSize) {
            if (i1 >arr1.si)
        }

        return result;
    }
}
