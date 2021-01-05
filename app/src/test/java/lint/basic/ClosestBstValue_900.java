package lint.basic;

import org.junit.Test;

public class ClosestBstValue_900 {
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
        ret = Integer.MAX_VALUE;
        dfs(root, target);
        return ret;
    }

    private void dfs(TreeNode root, double target) {
        if (root == null) {
            return;
        }
        dfs(root.left, target);
        if (Math.abs(ret - target) > Math.abs(root.val - target)) {
            ret = root.val;
        }
        dfs(root.right, target);
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