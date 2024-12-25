package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 92. Reverse Linked List II
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the
 * list from position left to position right, and return the reversed list.
 * Example 1:
 *   Input: head = [1,2,3,4,5], left = 2, right = 4
 *   Output: [1,4,3,2,5]
 * Example 2:
 *   Input: head = [5], left = 1, right = 1
 *   Output: [5]
 * Constraints:
 *   The number of nodes in the list is n.
 *   1 <= n <= 500
 *   -500 <= Node.val <= 500
 *   1 <= left <= right <= n
 * Follow up: Could you do it in one pass?
 */
public class LC0092 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode headNode = new ListNode(-1), tailNode = headNode, currNode = head;

        int index = 1;
        while (index < m) {
            tailNode.next = currNode;
            currNode = currNode.next;
            tailNode = tailNode.next;
            tailNode.next = null;
            index++;
        }

        ListNode reversedList = new ListNode(-1), rtail = null;
        while (m <= index && index <= n) {
            ListNode rhead = reversedList.next;
            ListNode nextNode = currNode.next;
            reversedList.next = currNode;
            currNode.next = rhead;

            if (rhead == null) {
                rtail = currNode;
            }

            currNode = nextNode;
            index++;
        }

        tailNode.next = reversedList.next;
        rtail.next = currNode;

        return headNode.next;
    }
}
