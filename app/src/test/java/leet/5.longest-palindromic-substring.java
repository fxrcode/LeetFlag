package leet;

/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
class Solution {
    // private int maxLen = 0;
    // private String result = "";

    public String longestPalindrome(String ss) {
        if (ss == null || ss.length() == 0) {
            return "";
        }
        char[] s = ss.toCharArray();
        int n = s.length;
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            f[i][i] = true;
        }
        int start, maxlen;
        start = 0;
        maxlen = 1;
        for (int i = 0; i < n-1; i++) {
            if (s[i] == s[i+1]) {
                f[i][i+1] = true;
                start = i;
                maxlen = 2;
            }
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                f[i][j] = f[i+1][j-1] && (s[i] == s[j]);
                if (f[i][j] && len > maxlen) {
                    maxlen = len;
                    start = i;
                }
            }
        }
        return ss.substring(start, start+maxlen);
    }
}
// @lc code=end
