package lint;

import org.junit.Test;

public class ClosestBstValue_900_c {
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
        System.out.println(closestValue(root, 1.6) );
    }

    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        TreeNode lower, upper;
        lower = upper = root;
        while (root != null) {
            if (target > root.val) {
                lower = root;
                root = root.right;
            } else if (target < root.val) {
                upper = root;
                root = root.left;
            } else {
                return root.val;
            }
        }
        if (upper.val - target <= target - lower.val) {
            return upper.val;
        } else {
            return lower.val;
        }
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