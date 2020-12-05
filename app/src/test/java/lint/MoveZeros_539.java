package lint;

import java.util.Arrays;

import org.junit.Test;

public class MoveZeros_539 {
    @Test
    public void test() {
        int[] A = {0,1,2,3,4}; // {1, 0, 0, 3}; // {0, 1, 0, 3, 12};
        moveZeroes3(A);
        System.out.println(Arrays.toString(A));
    }

    /**
     * No need to be stable order, use partition: reverse 2 pointers.
     * @param nums
     */
    public void moveZeros(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i<j && nums[i] != 0) {
                i++;
            }
            while (i<j && nums[j] == 0) {
                j--;
            }
            if (i < j && nums[i] == 0 && nums[j] != 0) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
    }
    /**
     * stable swap, but worst case is O(2n) write operations
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

    /**
     * Stable move zeros, but no swap, so total is O(n) write operations.
     * @param nums
     */
    public void moveZeroes3(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        while (left < nums.length) {
            nums[left] = 0;
            left++;
        }
    }

    /**
     * @param nums
     */
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
