package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 83. Remove Duplicates from Sorted List
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the
 * linked list sorted as well.
 * Example 1:
 *   Input: head = [1,1,2]
 *   Output: [1,2]
 * Example 2:
 *   Input: head = [1,1,2,3,3]
 *   Output: [1,2,3]
 * Constraints:
 *   The number of nodes in the list is in the range [0, 300].
 *   -100 <= Node.val <= 100
 *   The list is guaranteed to be sorted in ascending order.
 */
public class LC0083 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode currentNode = head;
        int curVal = head.val;

        while(currentNode != null) {
            ListNode nextNode = currentNode.next;
            if (nextNode == null) break;

            int nextVal = nextNode.val;

            if (curVal != nextVal) {
                curVal = nextVal;
                currentNode = currentNode.next;
            } else {
                currentNode.next = nextNode.next;
            }
        }

        return head;
    }
}
