package lint.basic;

import org.junit.Test;

public class MinimumSubtree_596 {
    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(-5);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(2);
        TreeNode e = new TreeNode(-4);
        TreeNode f = new TreeNode(-5);
        root.left = a;
        root.right = b;
        a.left = c;
        a.right = d;
        b.left = e;
        b.right = f;
        Solution sln = new Solution();
        TreeNode subtree = sln.findSubtree(root);
        System.out.println( subtree.val );
    }

    // DFS's Divide & Conquer on Binary Tree example 1
    public class Solution {
        /**
         * @param root: the root of binary tree
         * @return: the root of the minimum subtree
         */
        public TreeNode findSubtree(TreeNode root) {
            // write your code here
            return dfs(root).subtree;
        }

        private ResultType dfs(TreeNode root) {
            if (root == null) {
                return new ResultType(Integer.MAX_VALUE, null, 0);
            }

            ResultType l_result = dfs(root.left);
            ResultType r_result = dfs(root.right);

            int sum = l_result.sum + r_result.sum + root.val;
            if (l_result.minsum < Math.min(sum, r_result.minsum)) {
                return new ResultType(l_result.minsum, l_result.subtree, sum);
            }
            else if (r_result.minsum < Math.min(sum, l_result.minsum)) {
                return new ResultType(r_result.minsum, r_result.subtree, sum);
            } else {
                return new ResultType(sum, root, sum);
            }
        }
    }

    class ResultType {
        int minsum;
        TreeNode subtree;   // real minimum root
        int sum; // sum including the root;
        public ResultType(int minsum, TreeNode t, int sum) {
            this.minsum = minsum;
            this.subtree = t;
            this.sum = sum;
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
