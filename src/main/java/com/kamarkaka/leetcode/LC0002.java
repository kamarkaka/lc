package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;
import com.kamarkaka.common.Utilities;

/***
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *   Input: l1 = [2,4,3], l2 = [5,6,4]
 *   Output: [7,0,8]
 *   Explanation: 342 + 465 = 807.
 *
 * Example 2:
 *   Input: l1 = [0], l2 = [0]
 *   Output: [0]
 *
 * Example 3:
 *   Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 *   Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 *   The number of nodes in each linked list is in the range [1, 100].
 *   0 <= Node.val <= 9
 *   It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class LC0002 {
   public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      ListNode res = new ListNode(0);
      ListNode head = res, prev = null;

      int carry = 0;
      while (l1 != null || l2 != null) {
         int val1 = 0, val2 = 0;
         if (l1 != null) {
            val1 = l1.val;
            l1 = l1.next;
         }
         if (l2 != null) {
            val2 = l2.val;
            l2 = l2.next;
         }

         int sum = val1 + val2 + carry;
         int val = sum % 10;
         carry = sum / 10;

         res.val = val;
         res.next = new ListNode(0);
         prev = res;
         res = res.next;
      }

      res.val = carry;
      if (res.val == 0 && prev != null) prev.next = null;
      return head;
   }

   public static void run() {
      LC0002 solution = new LC0002();
      Utilities.print(solution.addTwoNumbers(
            new ListNode(2, new ListNode(4, new ListNode(3, null))),
            new ListNode(5, new ListNode(6, new ListNode(4, null)))));
   }
}
