package lint;

import org.junit.Test;

public class MaximumSubarray_41_b {
    @Test
    public void test() {
        int[] nums = { -2, 1, -3, 4 };
        System.out.println(maxSubArray(nums));
    }

    /**
     * O(N). 9chap solution
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code here
        // refixSum: prefix sum of first i; max_sum: global max of
        // first i, min_sum: minimum sum of first i-1
        int prefixSum = 0;
        int max_sum = Integer.MIN_VALUE;
        int min_sum = 0; // NOT Integer.MAX_VALUE! because min_sum of first i-1 = 0, so given nums =
                         // [-1], then you got correct
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            max_sum = Math.max(prefixSum - min_sum, max_sum);
            min_sum = Math.min(min_sum, prefixSum);
        }
        return max_sum;
    }
}
