package segmenttreebit;

public class SegmentTreeBuild_201 {
    public SegmentTreeNode build(int start, int end) {
        return helper(start, end);
    }

    private SegmentTreeNode helper(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) {
            return root;
        }
        int mid = (start+end)/2;
        root.left = helper(start, mid);
        root.right = helper(mid+1, end);
        return root;
    }

    // Definition of SegmentTreeNode:
    public class SegmentTreeNode {
        public int start, end;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = this.right = null;
        }
    }

}
