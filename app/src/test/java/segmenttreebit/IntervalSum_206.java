package segmenttreebit;

import java.util.*;

import org.junit.Test;

public class IntervalSum_206 {
    @Test
    public void test1() {
        int[] A = { 1, 2, 7, 8, 5 };
        List<Interval> queries = Arrays.asList(new Interval(0, 4), new Interval(1, 2), new Interval(2, 4));
        List<Long> ans = intervalSum(A, queries);
        System.out.println(ans);
    }

    // private member
    /**
     * @param A:       An integer list
     * @param queries: An query list
     * @return: The result list
     */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        // write your code here

        SegmentNode root = build(A);
        // System.out.println(root.left.val);
        List<Long> result = new ArrayList<>();
        for (Interval intl : queries) {
            result.add(query(root, intl.start, intl.end));
        }
        return result;
    }

    private SegmentNode build(int[] A) {
        return buildHelper(0, A.length - 1, A);
    }

    private SegmentNode buildHelper(int start, int end, int[] A) {
        if (start > end) {
            return null;
        }
        SegmentNode root = new SegmentNode(start, end, A[start]);
        if (start == end) {
            return root;
        }
        int mid = (start + end) / 2;
        root.left = buildHelper(start, mid, A);
        root.right = buildHelper(mid + 1, end, A);
        root.val = root.left.val + root.right.val;
        return root;
    }

    /**
     * Query range never change in recursion, need to compare Query against root.mid
     * @param root
     * @param start
     * @param end
     * @return
     */
    private long query(SegmentNode root, int start, int end) {
        if (start <= root.start && root.end <= end) {
            return root.val;
        }
        // WRONT: int mid = (start, end)/2
        int mid = (root.start + root.end)/2;
        long sum_res = 0;
        if (start <= mid) {
            // sum_res += query(root.left, start, mid);
            sum_res += query(root.left, start, end);
        }
        if (end >= mid + 1) {
            // sum_res += query(root.right, mid+1, end);
            sum_res += query(root.right, start, end);
        }
        return sum_res;
    }

    class SegmentNode {
        int start, end, val; // val is sum [start, end]
        SegmentNode left, right;

        public SegmentNode(int s, int e, int v) {
            start = s;
            end = e;
            val = v;
            left = right = null;
        }
    }

    // Definition of Interval:
    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
