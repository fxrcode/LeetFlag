package lint.basic;

import org.junit.Test;

/**
 * Your submission beats 72.00% Submissions!
 */
public class ClosestBstValue_900_b {
    @Test
    public void test() {
        TreeNode root = new TreeNode(5);
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(4);
        root.left = a;
        a.left = b;
        a.right = d;
        b.right = c;
        closestValue(root, 1.6);
        System.out.println( ret );
    }

    private int ret;
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        double minn = Integer.MAX_VALUE;
        int ret = 0;
        while (root != null) {
            if (target > root.val) {
                if (Math.abs(root.val - target) <= minn) {
                    ret = root.val;
                    minn = Math.abs(root.val - target);
                }
                root = root.right;
            } else {
                if (Math.abs(root.val - target) <= minn) {
                    ret = root.val;
                    minn = Math.abs(root.val - target);
                }
                root = root.left;
            }
        }
        return ret;
    }

    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int v) {
            this.val = v;
            this.left = this.right = null;
        }
    }
}