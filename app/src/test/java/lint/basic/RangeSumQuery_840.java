package lint.basic;

import java.util.Arrays;

import org.junit.Test;

public class RangeSumQuery_840 {
    @Test
    public void test() {
        int[] A = {1,3,5};
        NumArray bit = new NumArray(A);
        System.out.println( bit.sumRange(0, 2) );
        bit.update(1,2);
        System.out.println( bit.sumRange(0, 2) );
    }

    class NumArray {
        private int[] A;
        private int[] bit;
        public NumArray(int[] nums) {
            this.A = new int[nums.length];
            this.bit = new int[nums.length+1];
            for (int i = 0; i < nums.length; i++) {
                update(i, nums[i]);
            }
            System.out.println( Arrays.toString(A));
            System.out.println( Arrays.toString(bit) );
        }

        public void update(int idx, int val) {
            int delta = val - A[idx];
            A[idx] = val;
            for (int i = idx+1; i <= A.length; i += lowbit(i)) {
                bit[i] += delta;
            }
        }

        public int sumRange(int i, int j) {
            return getPrefixSum(j) - getPrefixSum(i-1);
        }

        private int getPrefixSum(int idx) {
            int sum = 0;
            for (int i = idx+1; i > 0; i = i - lowbit(i)) {
                sum += bit[i];
            }
            return sum;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }

}
