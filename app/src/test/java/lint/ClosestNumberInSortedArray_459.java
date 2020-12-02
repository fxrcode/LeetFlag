package lint;

/*-
Description
Given a target number and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to the given target.

Return -1 if there is no element in the array.

There can be duplicate elements in the array, and we can return any of the indices with same value.

Example
Example 1:

Input: [1, 2, 3] , target = 2
Output: 1.
Example 2:

Input: [1, 4, 6], target = 3
Output: 1.
Example 3:

Input: [1, 4, 6], target = 5,
Output: 1 or 2.
Challenge
O(logn) time complexity.
*/
public class ClosestNumberInSortedArray_459 {
    public static void main(String[] args) {
        ClosestNumberInSortedArray_459 sln = new ClosestNumberInSortedArray_459();
        int[] A = {1,4,6};
        int target = 7;
        System.out.println( sln.closestNumber(A, target) );
    }
    /**
     * @param A:      an integer array sorted in ascending order
     * @param target: An integer
     * @return: an integer
     */
    public int closestNumber(int[] A, int target) {
        // find last <= target
        int left = findLowerClosest(A, target);
        int right = left+1;
        if ( leftCloser(A, target, left, right) ) {
            return left;
        } else {
            return right;
        }
    }

    private int findLowerClosest(int[] A, int target) {
        int start = 0, end = A.length-1;
        while (start + 1 < end) {
            int mid = (start+end) / 2;
            if (A[mid] < target) {
                start = mid;
            } else {
                end = mid; // so A[mid] = target will goto left half, so as to locate "last"
            }
        }
        if (A[end] < target) {
            return end;
        }
        if (A[start] < target) {
            return start;
        }
        return -1;
    }

    private boolean leftCloser(int[] A, int target, int left, int right) {
        if (left < 0) {
            return false;
        }
        if (right >= A.length) {
            return true;
        }
        return Math.abs(A[left]-target) < Math.abs(A[right]-target);
    }
}
