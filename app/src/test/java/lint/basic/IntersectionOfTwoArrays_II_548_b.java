package lint.basic;

import java.util.*;

import org.junit.Test;

public class IntersectionOfTwoArrays_II_548_b {
    @Test
    public void test() {
        int[] a = {4,7,9,7,6,7};
        int[] b = {5,0,0,6,1,6,2,2,4};
        int[] ans = intersection(a, b);

        System.out.println( Arrays.toString(ans) );
    }
    /**
     * Your submission beats 1.20% Submissions!
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // write your code here
        List<Integer> li = new ArrayList<>();
        Map<Integer, Integer> int2cnt = new HashMap<>();
        for (int n : nums1) {
            int2cnt.putIfAbsent(n, 0);
            int2cnt.put(n, int2cnt.get(n)+1);
        }

        System.out.println( int2cnt);
        for (int n : nums2) {
            if (int2cnt.getOrDefault(n, 0) != 0) {
                li.add(n);
                int2cnt.put(n, int2cnt.get(n)-1);
            }
        }

        Collections.sort(li);

        return li.stream().mapToInt(n->n).toArray();
    }
}
