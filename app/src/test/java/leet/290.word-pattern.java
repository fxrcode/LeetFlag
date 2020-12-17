import java.util.*;

/*
 * @lc app=leetcode id=290 lang=java
 *
 * [290] Word Pattern
 */

// @lc code=start
class Solution {

    /*-
    I'm using Word-Pattern-II's DFS template to tackle, which is not a good way to resolve this simple solution. Just a practice.
    36/36 cases passed (1 ms)
    Your runtime beats 82.02 % of java submissions
    Your memory usage beats 88.97 % of java submissions (36.8 MB)
    */
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Set<String> used = new HashSet<>();
        List<String> ss = Arrays.asList(s.split(" "));
        return dfs(pattern, ss, 0, map, used);
    }

    private boolean dfs(String p, List<String> ss, int idx, Map<Character, String> map, Set<String> used) {
        if (ss.size() <= idx || p.length() <= idx) {
            return ss.size() == p.length();
        }
        // if char in map
        char ch = p.charAt(idx);
        if (map.containsKey(ch)) {
            String p2s = map.get(p.charAt(idx));
            if (!ss.get(idx).equals(p2s)) {
                return false;
            }
            return dfs(p, ss, idx+1, map, used);
        }
        // if char not in map
        String p2s = ss.get(idx);
        if (used.contains(p2s)) {
            return false;
        }
        map.put(ch, p2s);
        used.add(p2s);
        if (dfs(p, ss, idx+1, map, used)) {
            return true;
        }
        used.remove(p2s);
        map.remove(ch);
        return false;
    }
}
// @lc code=end

