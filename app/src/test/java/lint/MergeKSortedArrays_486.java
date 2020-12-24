package lint;

import java.util.*;

import org.junit.Test;

public class MergeKSortedArrays_486 {
    @Test
    public void test() {
        int[][] arrays = {
            {1,3,5,7},
            {},
            {0,8,9,10,11}
        };
        int[] ans = mergekSortedArrays(arrays);
        System.out.println(Arrays.toString(ans));
    }
    /**
     * Time Limit Exceeded! Powered by LintCode FlashJudge
     * 92% test cases passed. Total runtime 3307 ms
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        Comparator<Node> compMin = (n1,n2) -> {
            return n1.v - n2.v;
        };
        List<Integer> ret = new ArrayList<>();

        Queue<Node> pq = new PriorityQueue<>(compMin);
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].length == 0) {
                continue;
            }
            pq.offer(new Node(i,0,arrays[i][0]));
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            ret.add(cur.v);
            if (cur.c + 1 < arrays[cur.r].length) {
                cur.c++;
                cur.v = arrays[cur.r][cur.c];
                pq.offer(cur);
            }
        }
        return ret.stream().mapToInt(i->i).toArray();
    }

    private class Node {
        int r,c;
        int v;

        public Node(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
}
