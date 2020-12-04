package lint;

import java.util.*;

/*-
460. Find K Closest Elements
Given target, a non-negative integer k and an integer array A sorted in ascending order, find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. Otherwise, sorted in ascending order by number if the difference is same.
Example

Example 1:

Input: A = [1, 2, 3], target = 2, k = 3
Output: [2, 1, 3]

Example 2:

Input: A = [1, 4, 6, 8], target = 3, k = 3
Output: [4, 1, 6]

Challenge

O(logn + k) time

*/
public class FindKClosestElements_460 {
    public static void main(String[] args) {
        FindKClosestElements_460 sln = new FindKClosestElements_460();
        int[] A = {1,4,6,8};
        int target = 10, k = 3;
        System.out.println( Arrays.toString( sln.kClosestNumbers(A, target, k) ));

        int[] B = {1,4,4,4, 8};
        target = 4;
        System.out.println( Arrays.toString( sln.kClosestNumbers(B, target, 4) ));
    }
    /**
     * @param A:      an integer array
     * @param target: An integer
     * @param k:      An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
        int right = searchInsert(A, target);
        int left = right - 1;
        System.out.println( left + " , " + right);
        int[] result = new int[k];
        for (int i = 0; i < k; ++i) {
            if (leftCloser(A, target, left, right)) {
                result[i] = A[left];
                left--;
            } else {
                result[i] = A[right];
                right++;
            }
        }
        return result;
    }

    /**
     * Find first position >= target
     * @param A
     * @param target
     * @return
     */
    private int searchInsert(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) /2;
            if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] >= target) {
            return start;
        }
        if (A[end] >= target) {
            return end;
        }
        return end+1;
    }

    private boolean leftCloser(int[] A, int target, int l, int r) {
        if (l < 0) {
            return false;
        }
        if (r >= A.length) {
            return true;
        }
        return (Math.abs(A[l]-target) < Math.abs(A[r]-target));
    }
}
