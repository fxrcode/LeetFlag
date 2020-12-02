package lint;

import java.util.*;

import org.junit.Test;

// 147
public class NarcissisticNumber_147 {

    @Test
    public void test() {
        // for (int i = 1; i < 8; i++) {
        // System.out.println( i + "-> " + getNarcissisticNumbers(i));
        // }
        // List<Integer> a = getNarcissisticNumbers(4);
        // System.out.println("Helllo");
        // System.out.println( a );

        // System.out.println( isNarcissNum(371));

        // build(1);
        dfsDriver(3);
    }

    /**
     * @param n: The number of digits
     * @return: All narcissistic numbers with n digits
     */
    public List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
        List<Integer> ans = new ArrayList<>();

        for (int i = pow(10, n - 1); i < pow(10, n); ++i) {
            int s = 0;
            int j = i;
            while (j != 0) {
                s += pow(j % 10, n);
                j = j / 10;
            }
            if (s == i) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean isNarcissNum(int num) {
        int s = 0;
        int j = num;
        String str = num + "";
        int n = str.length();
        System.out.println("n=" + n);
        while (j != 0) {
            s += pow(j % 10, n);
            j = j / 10;
        }
        System.out.println(s + "-> " + num);
        return s == num;
    }

    public static int pow(int base, int power) {
        return (int) Math.pow((double) base, (double) power);
    }

    // public static void build(int n) {
    //     boolean first = true;
    //     int comb = 0;
    //     for (int i = 0; i < n; i++) {
    //         int j = 0;
    //         if (first) {
    //             j = 1;
    //             first = !first;
    //         }
    //         for (; j < 10; j++) {
    //             comb = j + comb * 10;
    //         }
    //         if (i == n - 1) {
    //             System.out.println(comb);
    //             comb = 0;
    //         }
    //     }
    // }

    /**
     * Inspired by https://www.geeksforgeeks.org/count-of-n-digit-numbers-whose-sum-of-digits-equals-to-given-sum/
     * @param n
     */
    public static void dfsDriver(int n) {
        List<Integer> ans = new ArrayList<>();

        // 1st digit (highest) should not use 0
        for (int i = 1; i < 10; i++) {
            int sum = pow(i, n);
            dfs(n, n-1, sum, i, ans);
        }
        System.out.println( ans );
    }

    /**
     *
     * @param n CONST: size of narcissisc
     * @param left how many digit left?
     * @param sum narcissic sum from n -> n-left
     * @param real the real num, compare with sum when idx = 0
     * @return
     */
    private static void dfs(int n, int left, int sum, int real, List<Integer> ans) {
        if (left == 0) {
            if (sum == real) {
                ans.add(real);
                System.out.println(real);
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            sum += pow(i, n);
            real = real * 10 + i;
            dfs(n, left-1, sum, real, ans);
            // after recursion goes back to previous digit, need to backtracking to recover sum/real for next i
            real /= 10;
            sum -= pow(i,n);
        }
    }

}
