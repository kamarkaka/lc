package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 82. Remove Duplicates from Sorted List II
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
 * from the original list. Return the linked list sorted as well.
 * Example 1:
 *   Input: head = [1,2,3,3,4,4,5]
 *   Output: [1,2,5]
 * Example 2:
 *   Input: head = [1,1,1,2,3]
 *   Output: [2,3]
 * Constraints:
 *   The number of nodes in the list is in the range [0, 300].
 *   -100 <= Node.val <= 100
 *   The list is guaranteed to be sorted in ascending order.
 */
public class LC0082 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode headNode = new ListNode(-1), tailNode = headNode;
        ListNode currNode = head;
        ListNode nextNode = head.next;

        while (nextNode != null) {
            if (currNode.val == nextNode.val) {
                int val = currNode.val;
                currNode = null;
                while (nextNode != null && nextNode.val == val) {
                    nextNode = nextNode.next;
                }

                if (nextNode != null) {
                    currNode = nextNode;
                    nextNode = nextNode.next;
                }
            } else {
                tailNode.next = currNode;
                tailNode = tailNode.next;
                tailNode.next = null;

                currNode = nextNode;
                nextNode = nextNode.next;
            }
        }

        if (currNode != null) {
            tailNode.next = currNode;
            tailNode = tailNode.next;
            tailNode.next = null;
        }

        return headNode.next;
    }
}
