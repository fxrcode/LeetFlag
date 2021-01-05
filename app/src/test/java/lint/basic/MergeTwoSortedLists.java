package lint.basic;

import org.junit.Test;

public class MergeTwoSortedLists {
    @Test
    public void test1() {
        ListNode l1 = null;
        ListNode n1 = new ListNode(0);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next =n3;
        ListNode l2 = n1;
        System.out.println( mergeTwoLists(l1, l2).val);
    }
    /**
     * Your submission beats 97.40% Submissions!
     * @param l1: ListNode l1 is the head of the linked list
     * @param l2: ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        // ListNode dummy1 = new ListNode(-1); dummy1.next = l1;
        // ListNode dummy2 = new ListNode(-2); dummy2.next = l2;
        ListNode dummy = new ListNode(0);
        ListNode ret = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            } else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }
        // while (l1 != null) {
        //     dummy.next = l1;
        //     l1 = l1.next;
        //     dummy = dummy.next;
        // }
        // while (l2 != null) {
        //     dummy.next = l2;
        //     l2 = l2.next;
        //     dummy = dummy.next;
        // }
        if (l1 != null) {
            dummy.next = l1;
        }
        if (l2 != null) {
            dummy.next = l2;
        }
        return ret.next;
    }

    // Definition for ListNode
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
