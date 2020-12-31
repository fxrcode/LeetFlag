package lint;

import java.util.Arrays;

import org.junit.Test;

public class MaximumSubarray_41 {
    @Test
    public void test() {
        int[] nums = {-2,1,-3,4};
        System.out.println( maxSubArray(nums));
    }
    /**
     * Time Complexity: O(N^2) to get all subarray Sum, and to win the maximum
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code here
        int[] prefixSum = new int[nums.length+1];

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i+1] = sum;
        }
        // System.out.println( Arrays.toString(prefixSum));

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int pre = prefixSum[j+1] - prefixSum[i+1-1];
                // System.out.println( i + "," + j + ": " + pre);
                max = Math.max(pre, max);
            }
        }
        return max;
    }
}
