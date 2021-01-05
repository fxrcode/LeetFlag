package segmenttreebit;

public class SegmentTreeQuery_II_247 {
    /*
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if (root == null) {
            return 0;
        }
        if (start <= root.start && root.end <= end) {
            return root.count;
        }
        int mid = (root.start + root.end) / 2 ;
        int count = 0;
        if (start <= mid) {
            count += query(root.left, start, end);
        }
        if (end >= mid + 1) {
            count += query(root.right, start, end);
        }
        return count;
    }

    // Definition of SegmentTreeNode:
    public class SegmentTreeNode {
        public int start, end, count;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
            this.left = this.right = null;
        }
    }
}
