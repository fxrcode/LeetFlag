package lint.basic;

import java.util.*;

import org.junit.Test;

/**
 * Your submission beats 24.80% Submissions!
 * This problem is implicit graph BFS shortest path, DFS for ALL solutions.
 * No need to build graph!
 */
public class WordLadder_II_121_d {
    @Test public void test() {
        String start = "a";
        String end = "c";
        Set<String> dict = new HashSet<>(Arrays.asList("a","b","c") );

        System.out.println( findLadders(start, end, dict));
    }

    @Test public void test2() {

        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log") );

        System.out.println( findLadders(start, end, dict));
    }

    private static final char[] a2z = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        dict.add(start);
        dict.add(end);

        // BFS dist as in Algs-DPV, it means: shortest distance from end to the word
        Map<String, Integer> distance = new HashMap<>();
        distance.put(end, 0);
        bfs(start, end, distance, dict);
        System.out.println( distance );

        List<List<String>> results = new ArrayList<>();
        List<String> path = new ArrayList<String>();
        path.add(start);
        dfs(start, end, distance, dict, path, results);
        return results;
    }

    /**
     * add shortest path from node's neighbor, and return results when node == target
     * @param cur
     * @param target
     * @param distance
     * @param dict
     * @param path
     * @param results
     */
    private void dfs(String cur, String target, Map<String, Integer> distance, Set<String> dict, List<String> path, List<List<String>> results) {
        if (cur.equals(target) ) {      // because neig is created by getNeighs as new String. == only compare by memory address!
            results.add(new ArrayList<>(path));
            return;
        }
        for (String neig : getNeighs(cur, dict)) {
            if (distance.get(neig) == distance.get(cur) - 1) {
                path.add(neig);
                System.out.println( path );
                dfs(neig, target, distance, dict, path, results);
                path.remove(path.size()-1);
            }
        }
    }

    private void bfs(String start, String end, Map<String, Integer> distance, Set<String> dict) {
        Set<String> visited = new HashSet<>();
        visited.add(end);
        distance.put(end, 0);

        Queue<String> q = new LinkedList<>();
        q.offer(end);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int dist_cur = distance.get(cur);
            for (String neig : getNeighs(cur, dict)) {
                if (!visited.contains(neig)) {
                    distance.put(neig, dist_cur+1);
                    visited.add(neig);
                    q.offer(neig);
                }
            }
        }
    }


    private static Set<String> getNeighs(String s, Set<String> dict) {
        Set<String> ret = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (char ch : a2z) {
                String new_s = s.substring(0, i) + ch + s.substring(i+1);
                if (new_s != s && dict.contains(new_s) ) {
                    ret.add(new_s);
                }
            }
        }
        return ret;
    }
}
