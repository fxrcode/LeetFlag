package lint.basic;

import java.util.*;

import org.junit.Test;

/*-
 * Your submission beats 63.00% Submissions!
 * The goal of efficient DFS is to get closer to end from start, rather get away from start!
 */
public class WordLadder_II_121_b {

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
        // System.out.println( G );
        Node node_s = G.get(start);
        Node node_e = G.get(end);

        List<List<String>> results = new ArrayList<>();
        // List<String> path = Arrays.asList(end); // this is FIXED-SIZE list, can't add more!
        List<String> path = new ArrayList<>();
        path.add(end);
        dfs(node_e, node_s, node_e.dist, path, results);
        return results;
    }

    private void dfs(Node node, Node start, int dist, List<String> path, List<List<String>> results) {
        if (dist == 0 && node.equals(start)) {
            // System.out.println( "\t" + path );
            // Collections.reverse(path);
            // results.add( new ArrayList<>( path ) );
            results.add( reverse(path) );
            return;
        }
        for (Node neig : node.neighSet) {
            if (neig.dist == dist- 1) {
                path.add(neig.v);
                dfs(neig, start, dist-1, path, results);
                path.remove(path.size()-1);
            }
        }
    }

    private static List<String> reverse(List<String> path) {
        List<String> ret = new ArrayList<>();
        for (int i = path.size() -1 ; i >= 0; i--) {
            ret.add(path.get(i));
        }
        return ret;
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
            // for (int i = 0; i < sz; i++) {
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
            // }
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
