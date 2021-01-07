package segmenttreebit;

import java.util.*;

import org.junit.Test;

/*-
Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000)
and an query list. For each query, give you an integer, return the number of element in the array that are
smaller than the given integer.
*/
public class CountOfSmallerNumber_248 {
    @Test
    public void test1() {
        int[] A = {1,2,7,8,5};
        int[] queries = {1,8,5};
        System.out.println( countOfSmallerNumber(A, queries));
    }

    @Test
    public void test2() {
        int[] A = {3,4,5,8};
        int[] queries = {2,4};
        System.out.println( countOfSmallerNumber(A, queries));
    }
    /**
     * Memory Limit Exceeded!!! Powered by LintCode FlashJudge
     * 38% test cases passed
     * @param A:       An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given
     *          integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        SegmentNode root = build(0, 100000);
        for (int a : A) {
            modify(root, a);
        }
        for (int q : queries) {
            ans.add(query(root, 0, q-1));
        }
        return ans;
    }

    private SegmentNode build(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentNode root = new SegmentNode(start, end, 0);
        if (start == end) {
            root.count = 0;
            return root;
        }
        int mid = (start + end) / 2;
        root.left = build(start, mid);
        root.right = build(mid + 1, end);
        return root;
    }

    public int query(SegmentNode root, int start, int end) {
        if (start <= root.start && root.end <= end) {
            return root.count;
        }
        int mid = (root.start + root.end) / 2;
        int count = 0;
        if (start <= mid) {
            count += query(root.left, start, end);
        }
        if (end >= mid+1) {
            count += query(root.right, start, end);
        }
        return count;
    }

    public void modify(SegmentNode root, int v) {
        if (root == null) {
            return;
        }
        if (root.start == root.end && root.start == v) {
            root.count += 1;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (v <= mid) {
            modify(root.left, v);
        } else {
            modify(root.right, v);
        }
        root.count = root.left.count + root.right.count;
    }

    class SegmentNode {
        int start, end, count;
        SegmentNode left, right;

        public SegmentNode(int s, int e, int c) {
            start = s;
            end = e;
            count = c;
            left = right = null;
        }
    }
}
