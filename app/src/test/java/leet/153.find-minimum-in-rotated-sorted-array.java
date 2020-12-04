/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length-1;
        while (start+1 < end) {
            int mid = (start+end)/2;
            if (nums[mid] <= nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return Math.min(nums[start], nums[end]);
    }
}
// @lc code=end

