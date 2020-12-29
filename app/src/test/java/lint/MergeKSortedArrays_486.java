package lint;

import java.util.*;

import org.junit.Test;

public class MergeKSortedArrays_486 {
    @Test
    public void test() {
        int[][] arrays = {
            {1,3,5,12},
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
        Comparator<Pair> compMin = (n1,n2) -> {
            return arrays[n1.r][n1.c] - arrays[n2.r][n2.c];
        };
        List<Integer> ret = new ArrayList<>();

        Queue<Pair> pq = new PriorityQueue<>(compMin);
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].length == 0) {
                continue;
            }
            pq.offer(new Pair(i,0));
        }
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            ret.add( arrays[cur.r][cur.c]);
            if (cur.c + 1 < arrays[cur.r].length) {
                cur.c++;
                pq.offer(cur);
            }
        }
        return ret.stream().mapToInt(i->i).toArray();
    }

    private class Pair {
        int r,c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
