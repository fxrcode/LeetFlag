package lint;

import org.junit.Test;

public class LCA_88 {
    @Test
    public void test(){
        TreeNode root = new TreeNode(42);
        TreeNode p = new TreeNode(-3);
        TreeNode q = new TreeNode(-2);
        root.left = p;
        root.right = q;
        TreeNode ans = lowestCommonAncestor(root, p, q);
        System.out.println( ans.val );
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left_lca = dfs(root.left, p, q);
        TreeNode right_lca = dfs(root.right, p, q);
        if (left_lca != null && right_lca != null) {
            return root;
        }
        return left_lca != null ? left_lca : right_lca;
    }

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
