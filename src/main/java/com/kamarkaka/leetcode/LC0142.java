package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 142. Linked List Cycle II
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 *
 * Example 1:
 *    Input: head = [3,2,0,-4], pos = 1
 *    Output: tail connects to node index 1
 *    Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 *    Input: head = [1,2], pos = 0
 *    Output: tail connects to node index 0
 *    Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 *    Input: head = [1], pos = -1
 *    Output: no cycle
 *    Explanation: There is no cycle in the linked list.
 *
 * Constraints:
 *    The number of the nodes in the list is in the range [0, 10^4].
 *    -10^5 <= Node.val <= 10^5
 *    pos is -1 or a valid index in the linked-list.
 *
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
public class LC0142 {
   public ListNode detectCycle(ListNode head) {
      if (head == null) return null;

      ListNode intersect = getIntersect(head);
      if (intersect == null) {
         return null;
      }

      ListNode ptr1 = head;
      ListNode ptr2 = intersect;
      while (ptr1 != ptr2) {
         ptr1 = ptr1.next;
         ptr2 = ptr2.next;
      }

      return ptr1;
   }

   private ListNode getIntersect(ListNode head) {
      ListNode tortoise = head;
      ListNode hare = head;

      while (hare != null && hare.next != null) {
         tortoise = tortoise.next;
         hare = hare.next.next;
         if (tortoise == hare) {
            return tortoise;
         }
      }

      return null;
   }
}
