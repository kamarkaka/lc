package com.kamarkaka;

import com.kamarkaka.common.ListNode;
import com.kamarkaka.common.Utilities;

/***
 * 369. Plus One Linked List
 * Given a non-negative integer represented as a linked list of digits, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example 1:
 *    Input: head = [1,2,3]
 *    Output: [1,2,4]
 *
 * Example 2:
 *    Input: head = [0]
 *    Output: [1]
 *
 * Constraints:
 *    The number of nodes in the linked list is in the range [1, 100].
 *    0 <= Node.val <= 9
 *    The number represented by the linked list does not contain leading zeros except for the zero itself.
 */
public class LC0369 {
   public ListNode plusOne(ListNode head) {
      ListNode dummyHead = new ListNode(0);
      dummyHead.next = head;
      ListNode notNine = dummyHead;
      while (head != null) {
         if (head.val != 9) {
            notNine = head;
         }
         head = head.next;
      }
      notNine.val++;
      notNine = notNine.next;
      while (notNine != null) {
         notNine.val = 0;
         notNine = notNine.next;
      }
      return dummyHead.val == 1 ? dummyHead : dummyHead.next;
   }

   public static void run() {
      LC0369 sol = new LC0369();

      ListNode node1 = new ListNode(9);
      ListNode node2 = new ListNode(0);
      ListNode node3 = new ListNode(9);
      node1.next = node2;
      node2.next = node3;

      Utilities.print(sol.plusOne(node1));
   }
}
