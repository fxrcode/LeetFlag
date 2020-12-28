package lint;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.Test;

public class IntersectionOfTwoArrays_547 {
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
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // write your code here
        // Set<Integer> set1 = new HashSet<>(Arrays.asList(nums1));
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }
        List<Integer> intersect = new ArrayList<>();

        for (int i : set2) {
            if (set1.contains(i)) {
                intersect.add(i);
            }
        }
        return intersect.stream().mapToInt(i->i).toArray();
    }
}
