package segmenttreebit;

import org.junit.Test;

/**
 * BIT template
 */
public class RangeSumQueryMutable_840 {
    @Test
    public void test() {
        int[] A = {1,3,5};
        NumArray bit = new NumArray(A);
        System.out.println( bit.sumRange(0, 2) );
        bit.update(1,2);
        System.out.println( bit.sumRange(0, 2) );
    }

    class NumArray {
        int[] bit, A;
        int n;

        public NumArray(int[] nums) {
            n = nums.length;
            A = new int[n];
            bit = new int[n+1];
            for (int i = 0; i < n; ++i) {
                update(i, nums[i]);
            }
        }

        public void update(int index, int val) {
            int delta = val - A[index];
            A[index] = val;
            for (int x = index+1; x <= n; x += lowbit(x)) {
                bit[x] += delta;
            }
        }

        private int getPrefixSum(int index) {
            int sum = 0;
            for (int x = index+1; x > 0; x -= lowbit(x)) {
                sum += bit[x];
            }
            return sum;
        }

        public int sumRange(int i, int j) {
            return getPrefixSum(j) - getPrefixSum(i-1);
        }

        private int lowbit(int a ) {
            return a & (-a);
        }
    }
}
