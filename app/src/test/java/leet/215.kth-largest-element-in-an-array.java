import java.util.Random;

/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 */

// @lc code=start
class Solution {
    // Your runtime beats 97.87 % of java submissions
    final Random rand = new Random();
    public int findKthLargest(int[] nums, int k) {
        int k_ = nums.length-k;
        return quickselect(nums, 0, nums.length-1, k_);
    }

    // return the kth element in an array
    private int quickselect(int[] nums, int start, int end, int k) {
        // System.out.println( start + "->" + end);
        if (start == end) {
            return nums[start];
        }
        int r = partition(nums, start, end);
        if (k < r) {
            return quickselect(nums, start, r-1, k);
        } else if (k > r) {
            return quickselect(nums, r+1, end, k);
        } else {
            return nums[r];
        }
    }

    private int partition(int[] nums, int start, int end) {
        // after I use random pivot, it beats 97%. If always end pivot, it's 20%.
        int p = rand.nextInt(end-start+1) + start;
        int pivot = nums[p];
        swap(nums, p, end);
        int l = start-1;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) {
                l++;
                swap(nums, l, i);
            }
        }
        swap(nums, end, l+1);
        return l+1;
    }

    private static void swap(int[] A, int i1, int i2) {
        int t = A[i1];
        A[i1] = A[i2];
        A[i2] = t;
    }
}
// @lc code=end

