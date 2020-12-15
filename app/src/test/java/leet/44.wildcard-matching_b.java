/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */

// @lc code=start
class Solution {
    /**
     * Using memoization search, Your submission beats 98.80% Submissions!
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        return dfs(s, 0, p, 0, memo, visited);
    }

    // return if s[i:] can match with p[j:]
    private boolean dfs(String s, int i, String p, int j, boolean[][] memo, boolean[][] visited) {
        // exit condition
        if (i == s.length()) {
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
        if (visited[i][j]) {
            return memo[i][j];
        }

        // matching
        if (p.charAt(j) != '*') {
            memo[i][j] = (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                        && dfs(s, i + 1, p, j + 1, memo, visited);
        } else {
            memo[i][j] = dfs(s, i, p, j + 1, memo, visited)
                        || dfs(s, i + 1, p, j, memo, visited);
            // the right one means: '*'' match 1 or more in s (this is real recursion)
        }
        visited[i][j] = true;
        return memo[i][j];
    }
}
// @lc code=end
