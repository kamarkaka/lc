package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 234. Palindrome Linked List
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * Example 1:
 *   Input: head = [1,2,2,1]
 *   Output: true
 * Example 2:
 *   Input: head = [1,2]
 *   Output: false
 * Constraints:
 *   The number of nodes in the list is in the range [1, 10^5].
 *   0 <= Node.val <= 9
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class LC0234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        if (head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;

        ListNode fastNode = head;
        ListNode slowNode = head;

        while (fastNode.next != null && fastNode.next.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        ListNode reversedListHead = reverseList(slowNode.next);

        while (reversedListHead != null) {
            if (head.val != reversedListHead.val) return false;

            head = head.next;
            reversedListHead = reversedListHead.next;
        }

        return true;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode listHead = head;
        ListNode currentNode = head;

        while (currentNode.next != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = nextNode.next;
            nextNode.next = listHead;
            listHead = nextNode;
        }

        return listHead;
    }
}
