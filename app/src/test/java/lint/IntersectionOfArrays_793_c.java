package lint;

import java.io.*;
import java.util.*;

import org.junit.Test;

import basic.Tio;

public class IntersectionOfArrays_793_c {
    @Test
    public void read() {
        ClassLoader cl = getClass().getClassLoader();
        File is = new File(cl.getResource("test1.txt").getFile());
        System.out.println( is.getAbsolutePath());
    }


    @Test
    public void test() throws IOException {
        int[][] arrs = {
            {1,2,4,5},
            {3,4,5,6},
            {4,9}
        };

        int[][] arrs2 = {
            {1,2},
            {2},
            {1,2}
        };

        List<List<Integer>> ll = Tio.readLint793("lint793.txt");
        System.out.println( ll.size());

        Integer[][] BIGG = new Integer[ll.size()][];
        for (int i = 0; i < ll.size(); i++) {
            List<Integer> row = ll.get(i);
            BIGG[i] = row.toArray(new Integer[row.size()]);
            // System.out.println( BIGG[i].length);
        }

        // System.out.println( intersectionOfArrays(arrs2) );
        System.out.println( intersectionOfArrays(BIGG) );
    }
    /**
     * Your submission beats 6.60% Submissions!
     * Using k size priority queue to mimic the sort.
     * say input is k arrays, each has N elements
     * TimeComplexity: O(kNLogN + nkLogk)
     * Space complex: O(k) << hashmap solution
     * @param arrs: the arrays
     * @return: the number of the intersection of the arrays
     */
    public int intersectionOfArrays(Integer[][] arrs) {
        // write your code here
        int intersection = 0;
        int lastNum = 0, count = 0;

        int k = arrs.length;

        Comparator<Pair> comp = (p1, p2) -> {
            return arrs[p1.r][p1.c] - arrs[p2.r][p2.c];
        };
        Queue<Pair> pq = new PriorityQueue<>(k, comp);
        for (int i = 0; i < arrs.length; i++) {
            if (arrs[i] == null || arrs[i].length == 0) {
                return 0;   // Just in case: no intersection from all arrs
            }
            Arrays.sort(arrs[i]);   // need to sort all arrays, before into q
            pq.offer(new Pair(i, 0));
        }

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            // compare and update count
            // if not same: next number, or first element of all
            if (arrs[p.r][p.c] != lastNum || count == 0) {
                if (count == k) {
                    intersection++;
                    // count = 1;   I always got it wrong.
                }
                lastNum = arrs[p.r][p.c];
                count = 1;
            } else {
                // if same
                count++;
            }
            // and offer next into pq
            if (p.c + 1 < arrs[p.r].length) {
                p.c++;
                pq.offer(p);
            }
        }

        if (count == k) {
            intersection++;
        }
        return intersection;
    }

    private class Pair {
        int r, c;
        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
