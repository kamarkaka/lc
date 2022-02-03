/***
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 *   Input: head = [1,2,3,4,5], k = 2
 *   Output: [2,1,4,3,5]
 *
 * Example 2:
 *   Input: head = [1,2,3,4,5], k = 3
 *   Output: [3,2,1,4,5]
 *
 * Example 3:
 *   Input: head = [1,2,3,4,5], k = 1
 *   Output: [1,2,3,4,5]
 *
 * Example 4:
 *   Input: head = [1], k = 1
 *   Output: [1]
 *
 * Constraints:
 * The number of nodes in the list is in the range sz.
 *   1 <= sz <= 5000
 *   0 <= Node.val <= 1000
 *   1 <= k <= sz
 *
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 */

package com.kamarkaka;

import com.kamarkaka.common.ListNode;
import com.kamarkaka.common.Utilities;

public class LC0025 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        if (k <= 1) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;
        ListNode pre = dummy;
        ListNode temp = head;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        temp = head;
        for (int i = 0; i < size / k; i++) {
            ListNode last = temp;
            for (int j = 0; j < k; j++) {
                ListNode node = temp.next;
                temp.next = pre;
                pre = temp;
                temp = node;
            }

            p.next = pre;
            last.next = temp;
            p = last;
            pre = last;
        }

        return dummy.next;
    }

    public static void run() {
        LC0025 solution = new LC0025();
        Utilities.print(solution.reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
        Utilities.print(solution.reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 3));
        Utilities.print(solution.reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 1));
        Utilities.print(solution.reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 9));
        Utilities.print(solution.reverseKGroup(new ListNode(1), 1));
    }
}
