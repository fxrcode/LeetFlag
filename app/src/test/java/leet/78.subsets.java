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
                    int index,
                    List<Integer> subset,
                    List<List<Integer>> results) {
        if (index == nums.length) {
            results.add(new ArrayList<>(subset));
            System.out.println(subset);
            return;
        }

        subset.add(nums[index]);
        dfs(nums, index+1, subset, results);

        subset.remove(subset.size()-1);
        dfs(nums, index+1, subset, results);
    }
}
// @lc code=end

