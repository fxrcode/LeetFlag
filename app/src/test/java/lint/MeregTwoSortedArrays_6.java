package lint;

import java.util.*;

import org.junit.Test;

public class MeregTwoSortedArrays_6 {
    @Test
    public void test() {
        int[] A = {1,2,3,4};
        int[] B = {2,4,5,6};
        System.out.println( Arrays.toString(mergeSortedArray(A, B) ));
    }
    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int i = 0, j = 0;
        int k = 0;
        List<Integer> ret = new ArrayList<>();
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                ret.add(A[i]);
                i++;
            } else {
                ret.add(B[j]);
                j++;
            }
        }
        while (i < A.length) {
            ret.add(A[i]);
            i++;
        }
        while (j < B.length) {
            ret.add(B[j]);
            j++;
        }
        // return (int[])ret.toArray();
        return ret.stream().mapToInt(r -> r).toArray();
    }
}
