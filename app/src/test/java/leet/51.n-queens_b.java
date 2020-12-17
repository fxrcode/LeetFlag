import java.util.*;

/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 */

// @lc code=start
class Solution {

    // Your runtime beats 12.46 % of java submissions
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        Map<String, Set<Integer>> visited = new HashMap<>();
        visited.put("col", new HashSet<>());
        visited.put("sum", new HashSet<>());
        visited.put("diff", new HashSet<>());
        dfs(n, new ArrayList<>(), visited, results);
        return results;
    }

    /**
     * Using permutation DFS template to speed up isValid from O(N) -> O(1)
     *
     * @param N
     * @param perm
     * @param visited
     * @param results
     */
    private void dfs(int N, List<String> perm, Map<String, Set<Integer>> visited, List<List<String>> results) {
        if (perm.size() == N) {
            results.add(draw(N, new ArrayList<>(perm)));
            return;
        }

        for (int c = 0; c < N; c++) {
            int r = perm.size();
            if (!isValid(N, visited, perm, c)) {
                continue;
            }
            perm.add(c+"");
            visited.get("col").add(c);
            visited.get("sum").add(c+r);
            visited.get("diff").add(r-c);
            dfs(N, perm, visited, results);
            visited.get("diff").remove(r-c);
            visited.get("sum").remove(c+r);
            visited.get("col").remove(c);
            perm.remove(perm.size()-1);
        }
    }

    /**
     * Check if col i can be added to new row of perm
     * @param perm
     * @param i
     * @return
     */
    private boolean isValid(int N, Map<String, Set<Integer>> visited, List<String> perm, int col) {
        int row = perm.size();
        if (visited.get("col").contains(col)) {
            return false;
        }
        if (visited.get("sum").contains(row+col)) {
            return false;
        }
        if (visited.get("diff").contains(row-col)) {
            return false;
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

