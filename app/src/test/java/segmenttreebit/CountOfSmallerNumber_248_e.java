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
public class CountOfSmallerNumber_248_e {
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
        int[] A = {  };
        int[] queries = { 86,59,39};
        System.out.println(countOfSmallerNumber(A, queries));
    }

    @Test
    public void test4() {
        int[] A = {55,81,56,91,35,92,10,53,27,94,64,45,19,44,52,19,79,12,16,90,97,33,73,2,20,68,19,7,17,62,45,48,62,26,85,4,63,67,56,16};
        int[] queries = {10,43,2,17,28,75,75,12};
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
     * 76% test cases passed
     *
     * @param A:       An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given
     *          integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        Bit bit = new Bit(A);
        List<Integer> li = new ArrayList<>();
        for (int q : queries) {
            if (q > 0)
                li.add( bit.getPrefixSum(q-1));
            else
                li.add( 0) ;
        }
        return li;
    }

    /**
     * Be careful on using the BIT template, the update used to update A[i]'s val. But now we're using A[i] as the BIT index.
     * So we should use delta = 1 when adding new A[i] into BIT!
     */
    class Bit {
        // private int[] arr;
        private int[] BIT;
        private int N;
        private int lowbit(int x) { return x & (-x); }

        public Bit(int[] A) {
            N = 10000;
            // arr = new int[N];
            BIT = new int[N+1];
            for (int i = 0; i < A.length; ++i) {
                // update(A[i], 1);
                increase(A[i], 1);
            }
        }

        // private void update(int index, int val) {
        //     int delta = val - arr[index];
        //     arr[index]  = val;

        //     for (int i = index+1; i <= N; i += lowbit(i)) {
        //         BIT[i] += delta;
        //     }
        // }

        private void increase(int idx, int delta) {
            for (int i = idx+1; i <= N; i += lowbit(i)) {
                BIT[i] += delta;
            }
        }
    
        private int getPrefixSum(int index) {
            int sum = 0;
            for (int i = index+1; i > 0; i -= lowbit(i)) {
                sum += BIT[i];
            }
            return sum;
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
