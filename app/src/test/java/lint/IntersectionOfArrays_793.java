package lint;

import java.io.*;
import java.util.*;

import org.junit.Test;

import basic.Tio;

public class IntersectionOfArrays_793 {
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

        int[][] arrs3 = {
            {1,2},
            {1,2,4},
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
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                all.add(arrs[i][j]);
            }
        }
        Collections.sort(all);
        // System.out.println(all);
        int k = arrs.length;

        int lastNum = -1, count = 0;
        int intersections = 0;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i) != lastNum || count == 0) {  // count = 0 is for all[0] element
                if (count == k) {
                    intersections++;
                }
                lastNum = all.get(i);
                count = 1;
            } else {
                count++;
            }
        }
        if (count == k) {
            intersections++;
        }
        return intersections;
    }
}
