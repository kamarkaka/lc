package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 203. Remove Linked List Elements
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 * Example 1:
 *   Input: head = [1,2,6,3,4,5,6], val = 6
 *   Output: [1,2,3,4,5]
 * Example 2:
 *   Input: head = [], val = 1
 *   Output: []
 * Example 3:
 *   Input: head = [7,7,7,7], val = 7
 *   Output: []
 * Constraints:
 *   The number of nodes in the list is in the range [0, 10^4].
 *   1 <= Node.val <= 50
 *   0 <= val <= 50
 */
public class LC0203 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode newHead = head;

        while (newHead != null && newHead.val == val) {
            newHead = newHead.next;
        }

        if (newHead == null) return null;

        ListNode previousNode = newHead;
        ListNode currentNode = newHead.next;

        while (currentNode != null) {
            if (currentNode.val == val) {
                previousNode.next = currentNode.next;
            } else {
                previousNode = currentNode;
            }

            currentNode = currentNode.next;
        }

        return newHead;
    }
}
