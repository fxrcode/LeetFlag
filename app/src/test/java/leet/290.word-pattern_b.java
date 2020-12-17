import java.util.*;

/*
 * @lc app=leetcode id=290 lang=java
 *
 * [290] Word Pattern
 */

// @lc code=start
class Solution {

    /*-
    Using Word-Pattern-II's DFS check logic.
    36/36 cases passed (1 ms)
    Your runtime beats 82.02 % of java submissions
    Your memory usage beats 24.65 % of java submissions (37.4 MB)    */
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Set<String> used = new HashSet<>();
        List<String> ss = Arrays.asList(s.split(" "));
        return check(pattern, ss, map, used);
    }

    private boolean check(String p, List<String> ss, Map<Character, String> map, Set<String> used) {
        if (p.length() != ss.size()) {
            return false;
        }

        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            String s = ss.get(i);
            // if map contains
            if (map.containsKey(ch)) {
                if (!s.equals(map.get(ch))) {
                    return false;
                } else {
                    continue;
                }
            } else {
                // if not contains
                if (used.contains(s)) {
                    return false;
                }
                used.add(s);
                map.put(ch, s);
            }
        }
        return true;
    }
}
// @lc code=end
