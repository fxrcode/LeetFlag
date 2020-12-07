package basic;

import java.util.Arrays;
import org.junit.Test;

public class Partition {

    @Test
    public void testLomuto() {
        int[] A= {4,3,2,1};
        System.out.println( Lomuto(A));
        System.out.println( Arrays.toString(A));
    }
    /*-
     * Nico Lomuto partition is easy to implement and good coding practice.
     * Introduced in Algorithms.wtf's Quicksort
     *
     * @param A input Array to partition by last element as pivot
     * @return pivot final correct index
     */
    public int Lomuto(int[] A) {
        int n = A.length;
        int p = n-1;
        int pivot = A[p];
        // swap(A, p, n-1);

        int l = -1;
        for (int i = 0; i < n-1; i++) {
            if (A[i] < pivot) {
                l = l+1;
                swap(A, l, i);
            }
        }
        swap(A, n-1, l+1);
        return l+1;
    }

    private static void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
