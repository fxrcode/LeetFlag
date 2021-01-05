package lint.basic;

import java.io.*;
import java.util.*;

import org.junit.Test;

import basic.Tio;

public class IntersectionOfArrays_793_b {
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

        Integer[][] ans = new Integer[ll.size()][];
        for (int i = 0; i < ll.size(); i++) {
            List<Integer> row = ll.get(i);
            ans[i] = row.toArray(new Integer[row.size()]);
            System.out.println( ans[i].length);
        }

        System.out.println( intersectionOfArrays(ans) );
    }
    /**
     * Your submission beats 54.20% Submissions!
     * simply put into a single list, and sorted, then do the trick as run-length-encoding
     * @param arrs: the arrays
     * @return: the number of the intersection of the arrays
     */
    public int intersectionOfArrays(Integer[][] arrs) {
        // write your code here
        Map<Integer, Integer> map = new HashMap<>();
        int k = arrs.length;
        int intersection = 0;
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                int num = arrs[i][j];
                map.putIfAbsent(num, 0);
                map.put(num, map.get(num)+1);
                if (map.get(num) == k) {
                    intersection++;
                }
            }
        }
        return intersection;
    }
}
