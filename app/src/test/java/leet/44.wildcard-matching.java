/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */

// @lc code=start
class Solution {
    /**
     * Failed for Time LImit Exceeded
     * 
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        return dfs(s, 0, p, 0);
    }

    /**
     * return if s[i:] can match with p[j:]
     *
     * @param s
     * @param i
     * @param p
     * @param j
     * @return
     */
    private boolean dfs(String s, int i, String p, int j) {
        // exit condition
        if (i >= s.length()) {
            for (int t = j; t < p.length(); t++) {
                if (p.charAt(t) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (j == p.length()) {
            return false;
        }

        // matching
        if (p.charAt(j) != '*') {
            return (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                    && dfs(s, i + 1, p, j + 1);
        }
        return dfs(s, i, p, j + 1)
            || dfs(s, i + 1, p, j);
            // the right one means: '*'' match 1 or more in s (this is real recursion)

        /*- I was thinking using a for loop, can't make it correct
         * boolean res = false;
         * for (int t = i+1; t < s.length(); t++) {
         *     res |= dfs(s, t, p, j);
         * }
         * return dfs(s, i, p, j+1) || res;
         */
    }
}
// @lc code=end
