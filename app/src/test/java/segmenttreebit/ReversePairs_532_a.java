package segmenttreebit;

import java.util.*;

import org.junit.Test;

public class ReversePairs_532_a {
    @Test
    public void test1() {
        int[] A = {2, 4, 1, 3, 5};
        System.out.println( reversePairs(A )) ;
    }

    @Test
    public void test2() {
        int[] A = {2, 4, 3, 5, 1};
        System.out.println( reversePairs(A )) ;
    }
    /**
     * Your submission beats 45.40% Submissions!
     * @param A: an array
     * @return: total of reverse pairs
     */
    public long reversePairs(int[] A) {
        // write your code here
        int cnt = 0;
        if (A == null || A.length == 0)
            return cnt;
        NumArray BIT = new NumArray(A);
        Map<Integer, Integer> val2rank = BIT.val2rank;
        int maxA = BIT.maxA;
        for (int i = 0; i < A.length; ++i) {
            // query
            long bigger = BIT.getPrefixSum( val2rank.get(maxA)) - BIT.getPrefixSum( val2rank.get(A[i]) ) ;

            // update
            BIT.increase(val2rank.get(A[i]), 1);
            System.out.println( A[i] + "->" + bigger);
            cnt += bigger;
        }
        return cnt;
    }

    class NumArray {
        int[] bit;
        int N;
        Map<Integer, Integer> val2rank;
        int maxA;
        /**
         * Building bit with index = discrete val of origin A[], so it's compact in numeric
         * @param A
         */
        public NumArray(int[] A) {

            // discrete BIT range
            val2rank = discrete(A);

            N = val2rank.keySet().size();
            bit = new int[N+1];

            System.out.println( val2rank );
            System.out.println( maxA);
            // for (int i = 0; i < A.length; ++i) {
            //     int rank = val2rank.get(A[i]);
            //     increase(rank, 1);
            // }
        }

        public long query(int A_val) {
            int rank = val2rank.get(A_val);
            return getPrefixSum(rank);
        }

        private void increase(int idx, int delta) {
            for (int i = idx+1; i <= N; i += lowbit(i)) {
                bit[i] += delta;
            }
        }

        private long getPrefixSum(int idx) {
            long sum = 0;
            for (int i = idx+1; i > 0 ; i -= lowbit(i)) {
                sum += bit[i];
            }
            return sum;
        }

        private int lowbit(int x) { return x & (-x); }

        private Map<Integer, Integer> discrete(int[] A) {
            Map<Integer, Integer> val2rank = new HashMap<>();
            Set<Integer> set = new HashSet<>();
            for (int a : A) {
                set.add(a);
            }
            List<Integer> li = new ArrayList<>(); li.addAll(set);
            Collections.sort(li);
            maxA = li.get(li.size()-1);

            for (int i = 0; i < li.size(); ++i) {
                val2rank.putIfAbsent(li.get(i), i);
            }

            return val2rank;
        }
    }
}
