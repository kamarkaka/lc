package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 148. Sort List
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * Example 1:
 *   Input: head = [4,2,1,3]
 *   Output: [1,2,3,4]
 * Example 2:
 *   Input: head = [-1,5,3,4,0]
 *   Output: [-1,0,3,4,5]
 * Example 3:
 *   Input: head = []
 *   Output: []
 * Constraints:
 *   The number of nodes in the list is in the range [0, 5 * 10^4].
 *   -10^5 <= Node.val <= 10^5
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class LC0148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0), node = result;
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        if (l1 != null) node.next = l1;
        if (l2 != null) node.next = l2;

        return result.next;
    }
}
