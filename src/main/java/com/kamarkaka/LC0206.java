/***
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Example 1:
 *   Input: head = [1,2,3,4,5]
 *   Output: [5,4,3,2,1]
 *
 * Example 2:
 *   Input: head = [1,2]
 *   Output: [2,1]
 *
 * Example 3:
 *   Input: head = []
 *   Output: []
 *
 * Constraints:
 *   The number of nodes in the list is the range [0, 5000].
 *   -5000 <= Node.val <= 5000
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
package main.java.com.kamarkaka;

import main.java.com.kamarkaka.common.ListNode;
import main.java.com.kamarkaka.common.Utilities;

public class LC0206 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = new ListNode(0, head);
        ListNode curr = head;
        while (curr.next != null) {
            ListNode next = curr.next;
            curr.next = next.next;

            head = pre.next;
            next.next = head;
            pre.next = next;
        }

        return pre.next;
    }

    public static void run() {
        LC0206 solution = new LC0206();

        Utilities.print(solution.reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))))));
        Utilities.print(solution.reverseList(new ListNode(1, new ListNode(2))));
        Utilities.print(solution.reverseList(null));
    }
}
