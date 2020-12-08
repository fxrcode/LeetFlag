package basic;

import org.junit.Test;

public class QuickSelect {
    @Test
    public void test() {
        int[] A = { 3, 4, 1, 2, 5 };
        int k = 3;
        System.out.println(kthSmallest(k, A));
    }

    /**
     * lint: 461. Kth Smallest Numbers in Unsorted Array
     * @param k:    An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // write your code here
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

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
