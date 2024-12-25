package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 86. Partition List
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater
 * than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * Example 1:
 *   Input: head = [1,4,3,2,5,2], x = 3
 *   Output: [1,2,2,4,3,5]
 * Example 2:
 *   Input: head = [2,1], x = 2
 *   Output: [1,2]
 * Constraints:
 *   The number of nodes in the list is in the range [0, 200].
 *   -100 <= Node.val <= 100
 *   -200 <= x <= 200
 */
public class LC0086 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;

        ListNode leftList = new ListNode(-1), leftTail = leftList;
        ListNode rightList = new ListNode(-1), rightTail = rightList;
        ListNode currNode = head;
        while (currNode != null) {
            ListNode nextNode = currNode.next;
            if (currNode.val < x) {
                leftTail.next = currNode;
                leftTail = leftTail.next;
                leftTail.next = null;
            } else {
                rightTail.next = currNode;
                rightTail = rightTail.next;
                rightTail.next = null;
            }
            currNode = nextNode;
        }

        leftTail.next = rightList.next;
        return leftList.next;

    }
}
