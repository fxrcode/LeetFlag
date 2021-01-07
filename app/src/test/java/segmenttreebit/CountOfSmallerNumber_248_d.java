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
public class CountOfSmallerNumber_248_d {
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

    /*-
     * Time Limit Exceeded
     * 81% test cases passed
     *
     * @param A:       An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given
     *          integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        Arrays.sort(A);
        List<Integer> li = new ArrayList<>();
        for (int q : queries) {
            li.add( indexOf(A, q));
        }
        return li;
    }

    // find 1st postition >= q in A
    private int indexOf(int[] A, int q) {
        if (A.length == 0) {
            return 0;
        }
        if (q < A[0]) {
            return 0;
        }
        if (q > A[A.length-1]) {
            return A.length-1;
        }
        int start = 0, end = A.length-1;
        while (start + 1 < end) {
            int mid = (start + end)  / 2;
            if (A[mid] == q) {
                end = mid;
            } else if (A[mid] < q) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[start] >= q) {
            return start;
        }
        if (A[end] >= q){
            return end;
        }
        return end+1;
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
