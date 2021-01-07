package segmenttreebit;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.junit.Test;

/*-
Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000)
and an query list. For each query, give you an integer, return the number of element in the array that are
smaller than the given integer.
*/
public class CountOfSmallerNumber_248_c {
    @Test
    public void test1() {
        int[] A = { 1, 2, 7, 8, 5 };
        int[] queries = { 1, 8, 5 };
        System.out.println(countOfSmallerNumber(A, queries));
    }

    @Test
    public void test2() {
        int[] A = { 3, 4, 5, 8 };
        int[] queries = { 2, 4 };
        System.out.println(countOfSmallerNumber(A, queries));
    }

    @Test
    public void test3() {
        int[] A = { 1, 2, 3, 4, 5, 6 };
        int[] queries = { 1, 2, 3, 4, -1, 6 };
        System.out.println(countOfSmallerNumber(A, queries));
    }

    @Test
    public void test4() {
        int[] A = {};
        int[] queries = { 1, 2, 3, 4, -1, 6 };
        System.out.println(countOfSmallerNumber(A, queries));
    }

    @Test
    public void test5() throws IOException {
        String file = "src/test/resources/" + "lint248.txt"; // "lint248_simple.txt";

        List<Integer> a = new ArrayList<>();
        List<Integer> q = new ArrayList<>();
        readInputs(a, q, file);

        int[] A = list2array(a);
        int[] queries = list2array(q);

        System.out.println( countOfSmallerNumber(A, queries) );
    }

    /**
     * Memory Limit Exceeded!!! Powered by LintCode FlashJudge 38% test cases passed
     * 
     * @param A:       An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given
     *          integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        int min = 10000, max = -100000;
        for (int a : A) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        System.out.println(Arrays.asList(min, max));
        SegmentNode root = build(min, max);
        for (int a : A) {
            modify(root, a);
        }
        for (int q : queries) {
            if (q <= min) {
                ans.add(0);
            } else if (q > max) {
                ans.add(A.length);
            } else {
                ans.add(query(root, min, q - 1));
            }
        }
        return ans;
    }

    private SegmentNode build(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentNode root = new SegmentNode(start, end, 0);
        if (start == end) {
            return root;
        }
        int mid = (start + end) / 2;
        root.left = build(start, mid);
        root.right = build(mid + 1, end);
        return root;
    }

    public int query(SegmentNode root, int start, int end) {
        if (start <= root.start && root.end <= end) {
            return root.count;
        }
        int mid = (root.start + root.end) / 2;
        int count = 0;
        if (start <= mid) {
            count += query(root.left, start, end);
        }
        if (end >= mid + 1) {
            count += query(root.right, start, end);
        }
        return count;
    }

    public void modify(SegmentNode root, int v) {
        if (root == null) {
            return;
        }
        if (root.start == root.end && root.start == v) {
            root.count += 1;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (v <= mid) {
            modify(root.left, v);
        } else {
            modify(root.right, v);
        }
        root.count = root.left.count + root.right.count;
    }

    class SegmentNode {
        int start, end, count;
        SegmentNode left, right;

        public SegmentNode(int s, int e, int c) {
            start = s;
            end = e;
            count = c;
            left = right = null;
        }
    }

    private static void readInputs(List<Integer> A, List<Integer> Q, String filepath) throws IOException {
        Path path = Paths.get(filepath);
        BufferedReader reader = Files.newBufferedReader(path);
        String line1 = reader.readLine();
        line1 = line1.substring(1, line1.length()-1 );
        // System.out.println( line1);
        String[] arrStr = line1.split("\s*,\s*");
        // System.out.println( Arrays.toString(arrStr));
        for (String a : arrStr) {
            A.add(Integer.parseInt(a));
        }

        String line2 = reader.readLine();
        line2 = line2.substring(1, line2.length()-1);
        String[] qStr = line2.split("\s*,\s*");
        // System.out.println( qStr );
        for (String q : qStr) {
            Q.add(Integer.parseInt(q));
        }

        reader.close();
    }

    private static Set<Integer> array2set(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int a: A) {
            set.add(a);
        }
        return set;
    }

    private static int[] list2array(List<Integer> li) {
        int[] array = new int[li.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = li.get(i);
        }
        return array;
    }
}
