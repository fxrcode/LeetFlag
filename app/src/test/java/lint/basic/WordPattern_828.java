package lint.basic;

import java.util.*;

import org.junit.Test;

public class WordPattern_828 {
    @Test
    public void test() {
        String p = "abb";
        String s = "dog cat cat";
        System.out.println( wordPattern( p, s));
    }

    // Your submission beats 33.20% Submissions!
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
            return dfs(p, ss, idx + 1, map, used);
        }
        // if char not in map
        String p2s = ss.get(idx);
        if (used.contains(p2s)) {
            return false;
        }
        map.put(ch, p2s);
        used.add(p2s);
        if (dfs(p, ss, idx + 1, map, used)) {
            return true;
        }
        used.remove(p2s);
        map.remove(ch);
        return false;
    }
}
