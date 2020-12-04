import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode id=658 lang=java
 *
 * [658] Find K Closest Elements
 */

// @lc code=start
class Solution {
    // O(nlogn) solution
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> is = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(is, (a,b) -> a == b ? a-b : Math.abs(a-x) - Math.abs(b-x));
        is = is.subList(0,k);
        Collections.sort(is);
        return is;
    }

    /**
     * My solution followed lintcode counterpart.
     * I prefer findIndex, since it's similar to Collections.BinarySearch.
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int r = findIndex(arr, x);
        int l = r-1;
        // This is the hard part that took me 1 hr to figure out
        for (int i = 0; i < k; i++) {
            if (leftCloser(arr, x, l, r)) {
                l--;
            } else {
                r++;
            }
        }
        // note: both l,r will be extra 1 after loop.
        List<Integer> ret = new ArrayList<>();
        for (int i = l+1; i < r; i++) {
            ret.add(arr[i]);
        }
        return ret;
    }

    /**
     * Same as Leet 35. Search Insert Position
     * @param arr
     * @param x
     * @return
     */
    private int findIndex(int[] arr, int x) {
        int start = 0, end = arr.length-1;
        while (start + 1 < end) {
            int mid = (start+end)/2;

            if (arr[mid] == x ) {
                end = mid;
            } else if (arr[mid] < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (arr[start] >= x) {
            return start;
        }
        if (arr[end] >= x) {
            return end;
        }
        return end+1;
    }

    /**
     * Actually, this is a comparator.
     * @param arr
     * @param x
     * @param l
     * @param r
     * @return
     */
    private boolean leftCloser(int[] arr, int x, int l, int r) {
        if (l < 0) {
            return false;
        }
        if (r >= arr.length) {
            return true;
        }
        if (Math.abs(arr[l]-x) < Math.abs(arr[r]-x)) {
            return true;
        }
        if (Math.abs(arr[l]-x) == Math.abs(arr[r]-x)) {
            return l < r;
        }
        return false;
    }
}
// @lc code=end

