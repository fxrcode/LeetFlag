/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        int left = 0;
        for (int r = 0; r < nums.length; ++r) {
            if (nums[r] != 0) {
                nums[left] = nums[r];
                left++;
            }
        }
        for (; left < nums.length; left++) {
            nums[left] = 0;
        }
    }
}
// @lc code=end

