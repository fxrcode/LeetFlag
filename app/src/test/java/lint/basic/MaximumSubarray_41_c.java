package lint.basic;

import org.junit.Test;

public class MaximumSubarray_41_c {
    @Test
    public void test() {
        int[] nums = {-2,1,-3};
        System.out.println( maxSubArray(nums));
    }
    /**
     * this problem was discussed by Jon Bentley (Sep. 1984 Vol. 27 No. 9 Communications of the ACM P885)
     * https://leetcode.com/problems/maximum-subarray/discuss/20211/Accepted-O(n)-solution-in-java
     * Time Complexity: O(N) using Bentley84.pdf linear scanning
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // a little different from Benltey: in here, subarray is required not empty, so init to nums[0] for both
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }
}
