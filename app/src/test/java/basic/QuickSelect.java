package basic;

import java.util.Random;

import org.junit.Test;

public class QuickSelect {
    private Random rand = new Random();
    @Test
    public void test() {
        int[] A = { 3, 2, 1, 5, 6, 4 };
        for (int k = 1; k <= A.length; k++) {
            System.out.println(kthSmallest(k, A));
        }
        // System.out.println(kthSmallest(1, A));
    }

    @Test
    public void testLomuto() {
        int[] A = {3, 4, 1, 2, 5};
        System.out.println( partition(A, 0, A.length-1, 2));
    }

    /**
     * lint: 461. Kth Smallest Numbers in Unsorted Array
     * @param k:    An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // write your code here
        // return quickSelect(nums, 0, nums.length - 1, k - 1);
        return quickSelectJeffE(nums, 0, nums.length-1, k-1);
    }

    /**
     * From JeffE's algorithms.wtf
     * @param nums
     * @param start
     * @param end
     * @param k
     * @return
     */
    public int quickSelectJeffE(int[] nums, int start, int end, int k) {
        // System.out.println( start + "->" + end);
        // edge case: return when nums size = 1
        if (start == end) {
            return nums[start];
        }

        // always pick the last elem as pivot
        int p = rand.nextInt(end-start+1) + start;
        int r = partition(nums, start, end, p);
        // System.out.println( "\t" + r);
        // the r has partitioned nums into 3 parts
        if (k < r) {
            return quickSelectJeffE(nums, start, r-1, k);
        } else if (k > r) {
            return quickSelectJeffE(nums, r+1, end, k);
        } else {
            return nums[r];
        }
    }

    /**
     * I think Nico Lomuto's partition is easier to understand, but need to be accurate in detail
     * @param nums
     * @param p // default to n-1, so always pick the last elem as Pivot
     * @return  // the "indexOf" nums[p]
     */
    private int partition(int[] nums, int start, int end, int p) {
        int pivot = nums[p];
        swap(nums, p, end);
        int l = start-1;    // index to elem < pivot, so this pointer to -1.
        for (int i = start; i < end; i++) {   // no need to process last elem
            if (nums[i] < pivot) {
                l++;    // one more less than
                swap(nums, l, i);   // swap
            }
        }
        swap(nums, l+1, end);
        return l+1;
    }

    private static void swap(int[] nums, int i1, int i2) {
        int t = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = t;
    }

    /**
     * From lintcode
     * @param nums
     * @param start
     * @param end
     * @param k
     * @return
     */
    public int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }

        int left = start, right = end;
        int pivot = nums[(left + right) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
                // only ++,-- when left <= right
                left++;
                right--;
            }
        }

        // when here, left > right
        if (right >= k && right >= start) {
            return quickSelect(nums, start, right, k);
        } else if (left <= k && left <= end) {
            return quickSelect(nums, left, end, k);
        } else {
            return nums[k];
        }

    }
}
