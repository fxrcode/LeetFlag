package lint.basic;

import java.util.*;

import org.junit.Test;

// Your submission beats 25.80% Submissions!
public class WordPatternII_829 {
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
        return dfs(pattern, str, 0, map, used);
    }

    private boolean dfs(String pattern, String str, int pi, Map<Character, String> map, Set<String> used) {
        if (pi == pattern.length()) {
            return str.length() == 0;
        }
        // if map contains
        char ch = pattern.charAt(pi);
        if (map.containsKey(ch)) {
            String p2s = map.get(ch);
            if (!str.startsWith(p2s)) { 
                return false;
            }
            return dfs(pattern, str.substring(p2s.length()), pi+1, map, used);
        } else {
            // if map doesn't contains pattern char, then select valid p2s to dfs.
            for (int i = 1; i <= str.length(); i++) {
                String p2s = str.substring(0, i);
                //
                if (used.contains(p2s)) {
                    // return false;    // should go for next candidate, rather false!!!
                    continue;
                }
                map.put(ch, p2s);
                used.add(p2s);
                if (dfs(pattern, str.substring(p2s.length()), pi+1, map, used)) {
                    return true;
                }
                used.remove(p2s);
                map.remove(ch);
            }

        }
        return false;
    }
}
