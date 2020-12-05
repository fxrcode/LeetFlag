package lint;

import java.util.Arrays;

import org.junit.Test;

public class MoveZeros_539 {
    @Test
    public void test() {
        int[] A = {1, 0, 0, 3}; // {0, 1, 0, 3, 12};
        moveZeroes(A);
        System.out.println(Arrays.toString(A));
    }
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }

    }
    public void moveZeroes2(int[] nums) {
        // write your code here
        int left = 0, right = 0;
        // find left to first 0 element, right to left+1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                left = i;
                right = left + 1;
                break;
            }
        }
        while (right < nums.length) {
            if (nums[right] != 0) {
                // swap
                swap(nums, left, right);
                left++;
            }
            right++;
        }

    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
