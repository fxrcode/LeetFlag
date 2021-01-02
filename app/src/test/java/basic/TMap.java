package basic;

import java.util.*;

import org.junit.Test;

public class TMap {

    /*-
     * He's note of Bellman-Ford & Dijkstra for 1514. Path with Maximum Probability, is quite good in Java8 & Python
     * https://leetcode.com/problems/path-with-maximum-probability/discuss/731767/JavaPython-3-2-codes%3A-Bellman-Ford-and-Dijkstra's-algorithm-w-brief-explanation-and-analysis
     */
    @Test
    public void testComputeOrDefault() {
        Map<Integer, List<int[]>> g = new HashMap<>();
        g.computeIfAbsent(0, l -> new ArrayList<>()).add(new int[] { 42, 798 });
        g.computeIfAbsent(0, l -> new ArrayList<>()).add(new int[] { 1, -3 });
        g.computeIfAbsent(0, l -> new ArrayList<>()).add(new int[] { 2, 17 });

        for (int i : new int[] { 0, 1 }) {
            System.out.println( i  + ": ");
            for (int[] a : g.getOrDefault(i, Collections.emptyList())) {
                System.out.println(Arrays.toString(a));
            }
        }
    }
}
