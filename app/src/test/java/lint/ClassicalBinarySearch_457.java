package lint;

/*-
Find any position of a target number in a sorted array. Return -1 if target does not exist.
 */
public class ClassicalBinarySearch_457 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,9,10,100};
        System.out.println( findPosition(nums, 100) );
        System.out.println( findPosition(nums, 20) );

        int[] nums2 = {1,1};
        System.out.println( findPosition(nums2, 1) );

    }
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public static int findPosition(int[] nums, int target) {
        // write your code here
        int start = 0, end = nums.length-1;
        while (start + 1 < end) {
            int mid = (start + end) >> 1;
            if (nums[mid] == target) {
                end = mid;
            }
            else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}