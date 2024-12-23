package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 445. Add Two Numbers II
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *   Input: l1 = [7,2,4,3], l2 = [5,6,4]
 *   Output: [7,8,0,7]
 *
 * Example 2:
 *   Input: l1 = [2,4,3], l2 = [5,6,4]
 *   Output: [8,0,7]
 *
 * Example 3:
 *   Input: l1 = [0], l2 = [0]
 *   Output: [0]
 *
 * Constraints:
 *   The number of nodes in each linked list is in the range [1, 100].
 *   0 <= Node.val <= 9
 *   It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * Follow up: Could you solve it without reversing the input lists?
 */
public class LC0445 {
   public ListNode reverseList(ListNode head) {
      ListNode last = null;
      while (head != null) {
         // keep the next node
         ListNode tmp = head.next;
         // reverse the link
         head.next = last;
         // update the last node and the current node
         last = head;
         head = tmp;
      }
      return last;
   }

   public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      // reverse lists
      l1 = reverseList(l1);
      l2 = reverseList(l2);

      ListNode head = null;
      int carry = 0;
      while (l1 != null || l2 != null) {
         // get the current values
         int x1 = l1 != null ? l1.val : 0;
         int x2 = l2 != null ? l2.val : 0;

         // current sum and carry
         int val = (carry + x1 + x2) % 10;
         carry = (carry + x1 + x2) / 10;

         // update the result: add to front
         ListNode curr = new ListNode(val);
         curr.next = head;
         head = curr;

         // move to the next elements in the lists
         l1 = l1 != null ? l1.next : null;
         l2 = l2 != null ? l2.next : null;
      }

      if (carry != 0) {
         ListNode curr = new ListNode(carry);
         curr.next = head;
         head = curr;
      }

      return head;
   }

   public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
      // find the length of both lists
      int n1 = 0, n2 = 0;
      ListNode curr1 = l1, curr2 = l2;
      while (curr1 != null) {
         curr1 = curr1.next;
         ++n1;
      }
      while (curr2 != null) {
         curr2 = curr2.next;
         ++n2;
      }

      // parse both lists
      // and sum the corresponding positions
      // without taking carry into account
      // 3->3->3 + 7->7 --> 3->10->10--> 10->10->3
      curr1 = l1;
      curr2 = l2;
      ListNode head = null;
      while (n1 > 0 && n2 > 0) {
         int val = 0;
         if (n1 >= n2) {
            val += curr1.val;
            curr1 = curr1.next;
            --n1;
         }
         if (n1 < n2) {
            val += curr2.val;
            curr2 = curr2.next;
            --n2;
         }

         // update the result: add to front
         ListNode curr = new ListNode(val);
         curr.next = head;
         head = curr;
      }

      // take the carry into account
      // to have all elements to be less than 10
      // 10->10->3 --> 0->1->4 --> 4->1->0
      curr1 = head;
      head = null;
      int carry = 0;
      while (curr1 != null) {
         // current sum and carry
         int val = (curr1.val + carry) % 10;
         carry = (curr1.val + carry) / 10;

         // update the result: add to front
         ListNode curr = new ListNode(val);
         curr.next = head;
         head = curr;

         // move to the next elements in the list
         curr1 = curr1.next;
      }

      // add the last carry
      if (carry != 0) {
         ListNode curr = new ListNode(carry);
         curr.next = head;
         head = curr;
      }

      return head;
   }
}
