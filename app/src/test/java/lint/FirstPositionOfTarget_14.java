package lint;

public class FirstPositionOfTarget_14 {
    public static void main(String[] args) {
        int[] nums = {1,4,4,5,7,7,8,9,9,10};
        System.out.println( binarySearch(nums, 4) );
    }
        /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public static int binarySearch(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length-1;
        while (start+1 < end) {
            int mid = (start+end) >> 1;
            // It's good to handle equal case in individual if branch
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
