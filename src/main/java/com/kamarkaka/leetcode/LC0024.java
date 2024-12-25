package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 24. Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying
 * the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * Example 1:
 *   Input: head = [1,2,3,4]
 *   Output: [2,1,4,3]
 * Example 2:
 *   Input: head = []
 *   Output: []
 * Example 3:
 *   Input: head = [1]
 *   Output: [1]
 * Example 4:
 *   Input: head = [1,2,3]
 *   Output: [2,1,3]
 * Constraints:
 *   The number of nodes in the list is in the range [0, 100].
 *   0 <= Node.val <= 100
 */
public class LC0024 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode node = head;
        ListNode parent = dummyNode;

        while (node != null && node.next != null) {
            parent.next = node.next;
            ListNode tmp = node.next.next;
            node.next.next = node;
            node.next = tmp;

            parent = node;
            node = node.next;
        }

        return dummyNode.next;
    }
}
