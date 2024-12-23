package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;
import com.kamarkaka.common.Utilities;

/***
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * Example 1:
 *   Input: head = [1,2,3,4,5]
 *   Output: [5,4,3,2,1]
 * Example 2:
 *   Input: head = [1,2]
 *   Output: [2,1]
 * Example 3:
 *   Input: head = []
 *   Output: []
 * Constraints:
 *   The number of nodes in the list is the range [0, 5000].
 *   -5000 <= Node.val <= 5000
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class LC0206 {
   public ListNode reverseList(ListNode head) {
      ListNode node = head;
      ListNode res = null;

      while (node != null) {
         ListNode next = node.next;

         if (res == null) {
            node.next = null;
            res = node;
         } else {
            node.next = res;
            res = node;
         }
         node = next;
      }

      return res;
   }

   public static void run() {
      LC0206 solution = new LC0206();

      Utilities.print(solution.reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))))));
      Utilities.print(solution.reverseList(new ListNode(1, new ListNode(2))));
      Utilities.print(solution.reverseList(null));
   }
}
