package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 61. Rotate List
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Example 1:
 *    Input: head = [1,2,3,4,5], k = 2
 *    Output: [4,5,1,2,3]
 *
 * Example 2:
 *    Input: head = [0,1,2], k = 4
 *    Output: [2,0,1]
 *
 * Constraints:
 *    The number of nodes in the list is in the range [0, 500].
 *    -100 <= Node.val <= 100
 *    0 <= k <= 2 * 10^9
 */
public class LC0061 {
   public ListNode rotateRight(ListNode head, int k) {
      if (head == null) return null;
      if (head.next == null) return head;

      ListNode oldTail = head;
      int n = 1;
      while (oldTail.next != null) {
         oldTail = oldTail.next;
         n++;
      }
      oldTail.next = head;

      ListNode newTail = head;
      for (int i = 0; i < n - k % n - 1; i++) {
         newTail = newTail.next;
      }
      ListNode newHead = newTail.next;
      newTail.next = null;

      return newHead;
   }
}
