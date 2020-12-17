import java.util.*;

/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 */

// @lc code=start
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        dfs(n, new ArrayList<>(), results);
        return results;
    }

    /**
     *
     * @param N
     * @param perm: {2,1,4,3} means col position for row 1,2,3,4.
     * @param results
     */
    private void dfs(int N, List<String> perm, List<List<String>> results) {
        if (perm.size() == N) {
            results.add(draw(N, new ArrayList<>(perm)));
            return;
        }

        for (int i = 0; i < N; i++) {
            int row = perm.size();
            if (!isValid(N, perm, i)) {
                continue;
            }
            perm.add(i+"");
            dfs(N, perm, results);
            perm.remove(perm.size()-1);
        }
    }

    /**
     * Check if col i can be added to new row of perm
     * @param perm
     * @param i
     * @return
     */
    private boolean isValid(int N, List<String> perm, int col) {
        int row = perm.size();
        for (int r = 0; r < perm.size(); r++) {
            int c = Integer.parseInt(perm.get(r));
            // same col
            if (c == col) {
                return false;
            }
            // X: left-to-right
            if (r - row == c - col) {
                return  false;
            }
            // X: right-to-left
            if (r+c == row+col) {
                return false;
            }
        }
        return true;
    }

    private List<String> draw(int N, List<String> perm) {
        List<String> board = new ArrayList<>();
        for (int r = 0; r < perm.size(); ++r) {
            int c = Integer.parseInt(perm.get(r));
            String b_row = "";
            for (int i = 0; i < N; i++) {
                if (i == c) {
                    b_row += "Q";
                } else {
                    b_row += ".";
                }
            }
            board.add(b_row);
        }
        return board;
    }
}
// @lc code=end

