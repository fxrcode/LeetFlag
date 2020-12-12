// import java.util.*;

/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
 */

// @lc code=start
//  Definition for a binary tree node.
class BSTIterator {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        while (root != null) {
            stack.add(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        TreeNode node = stack.peek();
        if (node.right != null) {
            TreeNode n = node.right;
            while (n != null) {
                stack.add(n);
                n = n.left;
            }
        } else {
            TreeNode n = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == n) {
                n = stack.pop();
            }

        }
        return node.val;
    }

    // public class TreeNode {
    //     int val;
    //     TreeNode left;
    //     TreeNode right;
    //     TreeNode() {}
    //     TreeNode(int val) { this.val = val; }
    //     TreeNode(int val, TreeNode left, TreeNode right) {
    //         this.val = val;
    //         this.left = left;
    //         this.right = right;
    //     }
    // }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end

