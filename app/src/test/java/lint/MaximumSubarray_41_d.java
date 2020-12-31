package lint;

import org.junit.Test;

public class MaximumSubarray_41_d {
    @Test
    public void test() {
        int[] nums = { -2, 1, -3, 4 };
        System.out.println(maxSubArray_v2(nums));
    }

    /**
     *
     * O(N) time, O(N) space
     * DP solution & some thoughts
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code here
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1] > 0? dp[i-1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * Improved space complex by using roling DP
     * @param nums
     * @return
     */
    public int maxSubArray_v2(int[] nums) {
        // write your code here
        int[] dp = new int[2];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i % 2] = dp[(i-1) % 2] > 0? dp[(i-1) % 2] + nums[i] : nums[i];
            max = Math.max(max, dp[i % 2]);
        }
        return max;
    }
}
