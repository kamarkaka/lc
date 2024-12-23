package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;
import com.kamarkaka.common.Utilities;

/***
 * 23. Merge k Sorted Lists
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 *    Input: lists = [[1,4,5],[1,3,4],[2,6]]
 *    Output: [1,1,2,3,4,4,5,6]
 *    Explanation: The linked-lists are:
 *       [
 *          1->4->5,
 *          1->3->4,
 *          2->6
 *       ]
 *       merging them into one sorted list:
 *       1->1->2->3->4->4->5->6
 *
 * Example 2:
 *    Input: lists = []
 *    Output: []
 *
 * Example 3:
 *    Input: lists = [[]]
 *    Output: []
 *
 * Constraints:
 *    k == lists.length
 *    0 <= k <= 10^4
 *    0 <= lists[i].length <= 500
 *    -10^4 <= lists[i][j] <= 10^4
 *    lists[i] is sorted in ascending order.
 *    The sum of lists[i].length will not exceed 104.
 */
public class LC0023 {
   public ListNode mergeKLists(ListNode[] lists) {
      if (lists.length == 0) {
         return null;
      }
      if (lists.length == 1) {
         return lists[0];
      }

      int interval = 1;

      while (interval < lists.length) {
         for (int i = 0; i + interval < lists.length; i = i + interval*2) {
            lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
         }
         interval *= 2;
      }
      return lists[0];
   }

   private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
      ListNode prehead = new ListNode (0);
      ListNode iterator = prehead;
      while (list1 != null && list2 != null) {
         if (list1.val < list2.val) {
            iterator.next = list1;
            list1 = list1.next;
         }
         else {
            iterator.next = list2;
            list2 = list2.next;
         }
         iterator = iterator.next;
      }
      if (list1 != null) {
         iterator.next = list1;
      }
      else {
         iterator.next = list2;
      }
      return prehead.next;
   }

   public static void run() {
      LC0023 solution = new LC0023();

      Utilities.print(solution.mergeKLists(new ListNode[] {
         new ListNode(1, new ListNode(4, new ListNode(5))),
         new ListNode(1, new ListNode(3, new ListNode(4))),
         new ListNode(2, new ListNode(6))
      }));
   }
}
