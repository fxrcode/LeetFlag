import java.util.*;
/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 */

// @lc code=start
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), results);
        return results;
    }

    private void dfs(int[] nums,
                    int startIdx,
                    List<Integer> subset,
                    List<List<Integer>> results) {
        results.add(new ArrayList<>(subset));
        for (int i = startIdx; i < nums.length; i++) {
            subset.add(nums[i]);
            dfs(nums, i+1, subset, results);
            subset.remove(subset.size()-1);
        }
    }
}
// @lc code=end

