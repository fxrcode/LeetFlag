package lint.basic;

import java.util.*;

import org.junit.Test;

// Your submission beats 100.00% Submissions!
public class WordPatternII_829_b {
    @Test public void test() {
        String p = "abab";
        String s = "redblueredblue";
        System.out.println( wordPatternMatch(p, s) );
        p = "";
        s = "s";
        System.out.println( wordPatternMatch(p, s) );
    }
    /**
     * @param pattern: a string,denote pattern string
     * @param str:     a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        Map<Character, String> map = new HashMap<>();
        Set<String> used = new HashSet<>();
        return dfs(pattern, 0, str, 0, map, used);
    }

    private boolean dfs(String pattern, int pi, String str, int si, Map<Character, String> map, Set<String> used) {
        if (pi == pattern.length()) {
            return str.length() == si;
        }
        if (si >= str.length()) {
            return false;
        }
        // if map contains
        char ch = pattern.charAt(pi);
        if (map.containsKey(ch)) {
            String p2s = map.get(ch);
            // if (str.substring(si, si+p2s.length()) != p2s) {
            if (!str.substring(si).startsWith(p2s)) {   // using substring may stringBoundaryException
                return false;
            }
            return dfs(pattern, pi+1, str, si+p2s.length(), map, used);
        } else {
            // if map doesn't contains pattern char, then select valid p2s to dfs.
            for (int i = si; i < str.length(); i++) {   // learn how to loop to select all str prefix
                String p2s = str.substring(si, i+1);
                // if pattern char not in map, but p2s used, it breaks bijection
                // so I should skip this p2s candidate
                if (used.contains(p2s)) {
                    // return false;
                    continue;
                }
                map.put(ch, p2s);
                used.add(p2s);
                if (dfs(pattern, pi+1, str, si+p2s.length(), map, used)) {
                    return true;
                }
                used.remove(p2s);
                map.remove(ch);
            }

        }
        return false;
    }
}
