package lint.basic;

import org.junit.Test;

public class IntervalSum_II_207 {

    @Test
    public void test1() {
        int[] A = { 1, 2, 7, 8, 5 };
        Solution s = new Solution(A);

        System.out.println(s.query(0, 2));
        s.modify(0, 4);
        System.out.println(s.query(0, 1));
        s.modify(2, 1);
        System.out.println(s.query(2, 4));
    }

    /**
     * This is my 1st segment Tree impl, so many if/else, start, mid, <=, order errors, be careful
     * Your submission beats 26.80% Submissions!
     */
    public class Solution {
        private SegmentNode root;
        /* you may need to use some attributes here */

        /*
         * @param A: An integer array
         */
        public Solution(int[] A) {
            // do intialization if necessary
            root = buildHelper(0, A.length-1, A);
        }

        private SegmentNode buildHelper(int left, int right, int[] A) {
            if (left > right) {
                return null;
            }
            if (left == right) {
                return new SegmentNode(left, right, A[left]);
            }
            SegmentNode root = new SegmentNode(left, right, 0);
            int mid = (left+right) / 2;

            root.left = buildHelper(left, mid, A);
            root.right = buildHelper(mid+1, right, A);
            root.sum = root.left.sum + root.right.sum;
            return root;
        }

        private long queryHelper(SegmentNode root, int start, int end) {
            if ( start <= root.start && root.end <= end ) {
                return root.sum;
            }

            int mid = (root.start + root.end) / 2;
            long sum_left, sum_right;
            sum_left = sum_right = 0;
            if (start <= mid) {
                sum_left = queryHelper(root.left, start, end);
            }
            if (end >= mid + 1) {
                sum_right = queryHelper(root.right, start, end);
            }
            return sum_left + sum_right;
        }

        private void modifyHelper(SegmentNode root, int index, int val) {
            if ( root.start == root.end && root.start  == index) {
                root.sum = val;
                return;
            }

            int mid = (root.start + root.end) / 2;
            if (index <= mid) {
                modifyHelper(root.left, index, val);
            } else {
                modifyHelper(root.right, index, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }

        /*
         * @param start: An integer
         *
         * @param end: An integer
         *
         * @return: The sum from start to end
         */
        public long query(int start, int end) {
            // write your code here
            return queryHelper(root, start, end);
        }

        /*
         * @param index: An integer
         *
         * @param value: An integer
         *
         * @return: nothing
         */
        public void modify(int index, int value) {
            // write your code here
            modifyHelper(root, index, value);
        }

        public long modify_T(int index, int value) {
            modify(index, value);
            return -999;
        }
    }

    class SegmentNode {
        int start, end, sum;
        SegmentNode left, right;
        public SegmentNode(int s, int e, int sum) {
            this.start = s;
            this.end = e;
            this.sum = sum;
            this.left = this.right = null;
        }
    }
}
