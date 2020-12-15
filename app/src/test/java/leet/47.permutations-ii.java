import java.util.*;
/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, visited, new ArrayList<Integer>(), results);
        return results;
    }

    private void dfs(int[] nums,
                    boolean[] visited,
                    List<Integer> permutation,
                    List<List<Integer>> results) {
        if (permutation.size() == nums.length) {
            results.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }
            // to avoid duplicate,
            if (i-1>= 0 && nums[i-1] == nums[i] && !visited[i-1]) {
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size()-1);
        }

    }
}
// @lc code=end
