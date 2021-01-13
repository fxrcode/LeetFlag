package segmenttreebit;

import java.util.*;

import org.junit.Test;

public class ReversePairs_leet_493 {
    @Test
    public void test1() {
        int[] A = { 1, 3, 2, 3, 1 };
        System.out.println(reversePairs(A));
    }

    @Test
    public void test2() {
        int[] A = { 2, 4, 3, 5, 1 };
        System.out.println(reversePairs(A));
    }

    @Test
    public void test3() {
        int[] A = { -5, -5 };
        System.out.println(reversePairs(A));
    }

    @Test
    public void test4() {
        int[] A = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        System.out.println( reversePairs(A));
    }

    /**
     * Failed at 134/137 test cases: test4 above. Due to Java int 32 bit limitation, while Python int has no limit.
     * 
     * @param A: an array
     * @return: total of reverse pairs
     */
    public int reversePairs(int[] A) {
        // write your code here
        int cnt = 0;
        if (A == null || A.length == 0)
            return cnt;
        int[] A_with_Ax2 = new int[A.length*2];
        for (int i = 0; i < A.length; ++i) {
            A_with_Ax2[i] = A[i];
        }

        for (int j = 0; j < A.length; ++j) {
            A_with_Ax2[j + A.length] = A[j] * 2;
        }
        NumArray BIT = new NumArray(A_with_Ax2);
        Map<Integer, Integer> val2rank = BIT.val2rank;
        for (int i = A.length-1; i >= 0 ; i--) {
            // query
            int smallerThanHalfA_i;
            smallerThanHalfA_i = BIT.getPrefixSum(val2rank.get(A[i]) - 1);

            // update
            BIT.increase(val2rank.get(A[i] * 2), 1);
            System.out.println( A[i] + "->" + smallerThanHalfA_i);
            cnt += smallerThanHalfA_i;
        }
        return cnt;
    }

    class NumArray {
        int[] bit;
        int N;
        Map<Integer, Integer> val2rank;
        int maxA;

        /**
         * Building bit with index = discrete val of origin A[], so it's compact in
         * numeric
         * 
         * @param A
         */
        public NumArray(int[] A) {

            // discrete BIT range
            val2rank = discrete(A);

            N = val2rank.keySet().size();
            bit = new int[N + 1];

            System.out.println(val2rank);
            System.out.println(maxA);
            // for (int i = 0; i < A.length; ++i) {
            // int rank = val2rank.get(A[i]);
            // increase(rank, 1);
            // }
        }

        public int query(int A_val) {
            int rank = val2rank.get(A_val);
            return getPrefixSum(rank);
        }

        private void increase(int idx, int delta) {
            for (int i = idx + 1; i <= N; i += lowbit(i)) {
                bit[i] += delta;
            }
        }

        private int getPrefixSum(int idx) {
            int sum = 0;
            for (int i = idx + 1; i > 0; i -= lowbit(i)) {
                sum += bit[i];
            }
            return sum;
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        private Map<Integer, Integer> discrete(int[] A) {
            Map<Integer, Integer> val2rank = new HashMap<>();
            Set<Integer> set = new HashSet<>();
            for (int a : A) {
                set.add(a);
            }
            List<Integer> li = new ArrayList<>();
            li.addAll(set);
            Collections.sort(li);
            maxA = li.get(li.size() - 1);

            for (int i = 0; i < li.size(); ++i) {
                val2rank.putIfAbsent(li.get(i), i);
            }

            return val2rank;
        }
    }
}
