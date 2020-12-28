package lint;

public class IntersectionOfTwoArrays_II_548 {
    /**
     * Your submission beats 1.00% Submissions!
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // write your code here
        List<Integer> li = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                
                li.add(nums1[i]);
                i++;
                j++;
            }
        }
        return li.stream().mapToInt(n->n).toArray();
    }
}
