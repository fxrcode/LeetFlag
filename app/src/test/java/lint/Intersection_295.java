package lint;

import java.util.*;

import org.junit.Test;

/*-
Description

Given two sorted interval sequences, each interval in the sequence does not intersect each other, and returns the index of the interval where the two sequences intersect
*/
public class Intersection_295 {
    @Test
    public void test() {
        List<List<Integer>> a = new ArrayList<>();
        a.add(Arrays.asList(0,3));
        a.add(Arrays.asList(7,10));
        List<List<Integer>> b = new ArrayList<>();
        b.add(Arrays.asList(-1,1));
        b.add(Arrays.asList(2,8));

        System.out.println( Intersection(a, b) );
    }
    /**
     * Thanks wjsugar's notes
     * Your submission beats 47.46% Submissions!
     * Using merge 2 sorted arrays' template, the hard part is how to compare interval vs compare int.
     * O(N+M)
     * @param a: first sequence
     * @param b: second sequence
     * @return: return ans
     */
    public List<List<Integer>> Intersection(List<List<Integer>> a, List<List<Integer>> b) {
        // write your code here
        List<List<Integer>> ret = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            // no Intersection
            if (a.get(i).get(0) > b.get(j).get(1)) {
                j++;
            } else if (a.get(i).get(1) < b.get(j).get(0) ) {
                i++;
            } else {
                // Intersection
                ret.add(Arrays.asList(i,j) );
                // 2 types of Intersection
                if (a.get(i).get(1) < b.get(j).get(1) ) {
                    i++;
                } else if (a.get(i).get(1) > b.get(j).get(1) ) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return ret;
    }

}
