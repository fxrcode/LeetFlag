package lint.basic;

public class LastPositionOfTarget_458 {
    public static void main(String[] args) {
        int[] nums = {1,4,4,4};
        System.out.println( lastPosition(nums, 4));
    }
        /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public static int lastPosition(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length-1;
        while (start+1 < end) {
            int mid = (start+end) >> 1;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            return end;
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }
}
