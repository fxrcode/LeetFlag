package segmenttreebit;

public class IntervalSum_II_207 {
    private SegmentNode root;
    /* you may need to use some attributes here */

    /*
    * @param A: An integer array
    */
    public IntervalSum_II_207(int[] A) {
        // do intialization if necessary
        root = build(0, A.length-1, A);
    }


        /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        // write your code here
        return queryHelper(root, start, end);
    }

    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void modify(int index, int value) {
        // write your code here
        modifyHelper(root, index, value);
    }


    private long queryHelper(SegmentNode root, int start, int end) {
        if (start <= root.start && root.end <= end) {
            return root.sum;
        }
        int mid = (root.start + root.end) / 2;
        int sum = 0;
        if (start <= mid) {
            sum += queryHelper(root.left, start, end);
        }
        if (end >= mid + 1) {
            sum += queryHelper(root.right, start, end);
        }
        return sum;
    }

    private void modifyHelper(SegmentNode root, int index, int value) {
        if (root.start == root.end && root.start == index) {
            root.sum = value;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (index <= mid) {
            modifyHelper(root.left, index, value);
        } else {
            modifyHelper(root.right, index, value);
        }
        // update root;
        root.sum = root.left.sum + root.right.sum;
    }

    private SegmentNode build(int start, int end, int[] A) {
        if (start > end) {
            return null;
        }
        SegmentNode root = new SegmentNode(start, end, A[start]);
        if (start == end) {
            return root;
        }
        int mid = (start+end) / 2;
        root.left = build(start, mid, A);
        root.right = build(mid+1, end, A);
        root.sum = root.left.sum + root.right.sum;
        return root;
    }

    class SegmentNode {
        int start, end, sum;
        SegmentNode left, right;

        public SegmentNode(int s, int e, int v) {
            start = s;
            end = e;
            sum = v;
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
