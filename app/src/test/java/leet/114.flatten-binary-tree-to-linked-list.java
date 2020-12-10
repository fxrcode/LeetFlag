/*
 * @lc app=leetcode id=114 lang=java
 *
 * [114] Flatten Binary Tree to Linked List
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        dfs(root);
    }

    // Divide & Conquer solution
    private TreeNode dfs(TreeNode root) {
        // edge case
        if (root == null) {
            return root;
        }
        TreeNode last_left = dfs(root.left);
        TreeNode last_right = dfs(root.right);
        // restructure
        if (last_left != null) {
            last_left.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        // return last node
        if (last_right != null) {
            return last_right;
        }
        if (last_left != null) {
            return last_left;
        }
        return root;
    }
}
// @lc code=end

