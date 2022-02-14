package main.java.com.kamarkaka;

import main.java.com.kamarkaka.common.ListNode;

import java.util.HashMap;
import java.util.Map;

/***
 * 19. Remove Nth Node From End of List
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 *   Input: head = [1,2,3,4,5], n = 2
 *   Output: [1,2,3,5]
 *
 * Example 2:
 *   Input: head = [1], n = 1
 *   Output: []
 *
 * Example 3:
 *   Input: head = [1,2], n = 1
 *   Output: [1]
 *
 * Constraints:
 *   The number of nodes in the list is sz.
 *   1 <= sz <= 30
 *   0 <= Node.val <= 100
 *   1 <= n <= sz
 *
 * Follow up: Could you do this in one pass?
 */
public class LC0019 {
   public ListNode removeNthFromEnd(ListNode head, int n) {
      int size = 0;

      ListNode node = head;
      while (node != null) {
         node = node.next;
         size++;
      }

      if (size == n) {
         head = head.next;
      } else {
         int i = 0;
         node = head;
         while (size - 1 - n > i) {
            node = node.next;
            i++;
         }

         node.next = node.next.next;
      }

      return head;
   }

   public ListNode removeNthFromEnd2(ListNode head, int n) {
      Map<Integer, ListNode> map = new HashMap<>();
      int size = 0;
      ListNode node = head;
      while (node != null) {
         map.put(size, node);

         node = node.next;
         size++;
      }

      if (size == n) {
         head = head.next;
         return head;
      }

      int index = size - 1 - n;
      node = map.get(index);
      node.next = node.next.next;
      return head;
   }
}
