package lint.basic;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.Test;

public class IntersectionOfTwoArrays_547_b {
    @Test
    public void test() {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};

        int[] intersect = intersection(nums1, nums2);
        for (int i : intersect) {
            System.out.println( i );
        }
    }
    /**
     * Using merge 2 sorted arrays template: 2 pointers, and ignore duplicate like in permutation template
     * Your submission beats 4.40% Submissions!
     * O(nlogn + mlogm + n + m)
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // write your code here
        // using merge 2 sorted arrays template
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> ret = new ArrayList<>();
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            // ignore duplicate, and go for next, then continue, so boundary check before doing biz logic
            if (i-1 >= 0 && nums1[i-1] == nums1[i]) {
                i++;
                continue;
            }
            if (j-1 >= 0 && nums2[j-1] == nums2[j]) {
                j++;
                continue;
            }
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                ret.add(nums1[i]);
                i++;
                j++;
            }
        }
        return ret.stream().mapToInt(n->n).toArray();
    }
}
