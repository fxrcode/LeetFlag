/*
 * @lc app=leetcode id=516 lang=java
 *
 * [516] Longest Palindromic Subsequence
 */

// @lc code=start
class Solution {
    public int longestPalindromeSubseq(String ss) {
        char[] s = ss.toCharArray();
        int n = ss.length();
        if (n <= 1)
            return n;

        int[][] f = new int[n][n];
        int i,j,len;
        for (i = 0; i < n; i++) {
            f[i][i] = 1;
        }
        for (i = 0; i < n-1; ++i) {
            f[i][i+1] = (s[i] == s[i+1]) ? 2 : 1;
        }

        for (len = 3; len <= n; len++){
            for (i = 0; i <= n-len; i++) {
                j = i + len -1;
                // j < n => i < n-len+1
                f[i][j] = Math.max(f[i+1][j], f[i][j-1]);
                if (s[i] == s[j]) {
                    f[i][j] = Math.max(f[i+1][j-1]+2, f[i][j]);
                }
            }
        }
        return f[0][n-1];
    }
}
// @lc code=end

