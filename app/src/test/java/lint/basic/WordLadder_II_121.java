package lint.basic;

import java.util.*;

import org.junit.Test;

/*-
 * Your submission beats 0.60% Submissions!
 * When you do DFS for paths, don't go for start+1, but go for end-1!
 */
public class WordLadder_II_121 {

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

    private final static String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    /**
     * @param start: a string
     * @param end:   a string
     * @param dict:  a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        dict.add(start);
        dict.add(end);
        Map<String, Node> G = buildG(new Node(start), dict);
        System.out.println( G );
        Node node_s = G.get(start);
        Node node_e = G.get(end);

        List<List<String>> results = new ArrayList<>();
        // List<String> path = Arrays.asList(end); // this is FIXED-SIZE list, can't add more!
        List<String> path = new ArrayList<>();
        path.add(start);
        dfs(node_s, node_e, 0, node_e.dist, path, results);
        return results;
    }

    private void dfs(Node node, Node end, int dist, int min, List<String> path, List<List<String>> results) {
        if (dist == min && node.equals(end)) {
            results.add( new ArrayList<>( path ) );
            return;
        }
        for (Node neig : node.neighSet) {
            if (neig.dist == dist + 1) {
                path.add(neig.v);
                dfs(neig, end, dist+1, min, path, results);
                path.remove(path.size()-1);
            }
        }
    }

    private Map<String, Node> buildG(Node s, Set<String> dict) {
        Queue<Node> q = new LinkedList<>();
        Map<String, Node> visited = new HashMap<>();
        q.offer(s);
        visited.put(s.v, s);
        s.dist = 0;
        while (!q.isEmpty()) {
            // Node cur = q.poll();
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                Set<String> neigs = findNeigbhorsStrings(cur.v, dict);
                for (String ns : neigs) {
                    if (!visited.containsKey(ns)) {
                        Node neig = new Node(ns);
                        neig.dist = cur.dist + 1;
                        visited.put(ns, neig);
                        cur.neighSet.add(neig);
                        q.offer(neig);
                    } else {
                        cur.neighSet.add(visited.get(ns));
                    }
                }
            }
        }
        return visited;
    }

    private Set<String> findNeigbhorsStrings(String ns, Set<String> dict) {
        Set<String> ret = new HashSet<>();
        for (int i = 0; i < ns.length(); i++) {
            for (char ch : ALPHA.toCharArray()) {
                String candidate = genNewString(ns, i, ch);
                if (!dict.contains(candidate)) {
                    continue;
                }
                ret.add(candidate);
            }
        }
        return ret;
    }

    private static String genNewString(String me, int i, char ch) {
        if (me.charAt(i) == ch) {
            return "";
        }
        String other = me.substring(0, i) + ch + me.substring(i + 1);
        return other;
    }

    protected class Node {
        String v;
        Set<Node> neighSet;
        int dist;

        public Node(String str) {
            this.v = str;
            this.dist = Integer.MAX_VALUE;
            neighSet = new HashSet<>();
        }

		@Override
		public String toString() {

            Set<String> ss = new HashSet<>();
            neighSet.forEach( node -> ss.add(node.v));
			return "Node [dist=" + dist + ", neighSet=" + ss + ", v=" + v + "]";
		}
    }
}
