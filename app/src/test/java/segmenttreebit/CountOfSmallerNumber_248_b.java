package segmenttreebit;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.Test;

/*-
Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000)
and an query list. For each query, give you an integer, return the number of element in the array that are
smaller than the given integer.
*/
public class CountOfSmallerNumber_248_b {
    @Test
    public void test1() {
        int[] A = {1,2,7,8,5};
        int[] queries = {1,8,5};
        System.out.println( countOfSmallerNumber(A, queries));
    }

    @Test
    public void test2() {
        int[] A = {3,4,5,8};
        int[] queries = {2,4};
        System.out.println( countOfSmallerNumber(A, queries));
    }

    @Test
    public void test3() {
        int[] A = {};
        int[] queries = {86,59,39};
        System.out.println( countOfSmallerNumber(A, queries));
    }

    @Test
    public void test4() {
        int[] A = {32, 67};
        int[] queries = {65, 50};
        System.out.println( countOfSmallerNumber(A, queries));
    }
    /**
     * Time Limit Exceeded!!! Powered by LintCode FlashJudge
     * 76% test cases passed
     * @param A:       An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given
     *          integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        // new HashSet<>(); dedup.addAll(Arrays.asList(A));
        // Set<Integer> dedup = Arrays.stream(A).boxed().collect(Collectors.toSet());
        Set<Integer> dedup = array2set(A);
        List<Integer> A_prime = new ArrayList<>();
        A_prime.addAll(dedup);
        Collections.sort(A_prime);
        Map<Integer, Integer> ori2index = new HashMap<>();
        for (int i = 0; i < A_prime.size(); ++i) {
            ori2index.put(A_prime.get(i), i);
        }
        System.out.println( ori2index);
        SegmentNode root = build(0, A_prime.size());
        for (int a : A) {
            modify(root, ori2index.get(a));
        }
        for (int q : queries) {
            int idx = -1;
            if (!ori2index.containsKey(q)) {
                // int[] a_prime = A_prime.stream().mapToInt(i->i).toArray();
                int[] a_prime = list2array(A_prime);
                idx = indexOf(a_prime, q);
            } else {
                idx = ori2index.get(q);
            }
            if (idx > 0) {
                ans.add(query(root, 0, idx-1));
            } else {
                ans.add(0);
            }
        }
        return ans;
    }

    /**
     * find the first position of q to insert in sorted A, aka, 1st position of A[i] >= q
     * aka. Leetcode 35: search insert position
     * @param A
     * @param q
     * @return
     */
    private int indexOf(int[] A, int q) {
        if (A.length == 0) {
            return 0;
        }
        int start = 0, end = A.length-1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (q < A[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A[start] >= q) {
            return start;
        }
        if (A[end] >= q) {
            return end;
        }
        return end+1;
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

    private SegmentNode build(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentNode root = new SegmentNode(start, end, 0);
        if (start == end) {
            root.count = 0;
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
        if (end >= mid+1) {
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
}
