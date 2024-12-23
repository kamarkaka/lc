package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;
import com.kamarkaka.common.Utilities;

/***
 * 21. Merge Two Sorted Lists
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Example 1:
 *    Input: list1 = [1,2,4], list2 = [1,3,4]
 *    Output: [1,1,2,3,4,4]
 *
 * Example 2:
 *    Input: list1 = [], list2 = []
 *    Output: []
 *
 * Example 3:
 *    Input: list1 = [], list2 = [0]
 *    Output: [0]
 *
 * Constraints:
 *    The number of nodes in both lists is in the range [0, 50].
 *    -100 <= Node.val <= 100
 *    Both list1 and list2 are sorted in non-decreasing order.
 */
public class LC0021 {
   public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
      ListNode curr = new ListNode(0), head = curr;

      while (list1 != null && list2 != null) {
         if (list1.val <= list2.val) {
            curr.next = new ListNode(list1.val);
            list1 = list1.next;
         } else {
            curr.next = new ListNode(list2.val);
            list2 = list2.next;
         }
         curr = curr.next;
      }

      if (list1 != null) {
         curr.next = list1;
      } else {
         curr.next = list2;
      }

      return head.next;
   }

   public static void run() {
      LC0021 solution = new LC0021();
      Utilities.print(solution.mergeTwoLists(
            new ListNode(1, new ListNode(2, new ListNode(4, null))),
            new ListNode(1, new ListNode(3, new ListNode(4, null)))));
   }
}
