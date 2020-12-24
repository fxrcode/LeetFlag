import java.util.*;

import lint.MergeTwoSortedLists.ListNode;

/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // Your runtime beats 24.99 % of java submissions
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode ret = dummy;

        Comparator<ListNode> comp = (n1,n2) -> {
            return n1.val - n2.val;
        };
        PriorityQueue<ListNode> pq = new PriorityQueue<>(comp);
        for (int i = 0; i < lists.length; ++i) {
            if (lists[i] == null) {
                continue;
            }
            pq.offer(lists[i]);
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            dummy.next = node;
            dummy = dummy.next;
            if (node.next != null) {
                pq.offer(node.next);
                // dummy = dummy.next;  // WRONG here at 1st try
            }
        }
        return ret.next;
    }

}
// @lc code=end

