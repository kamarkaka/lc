package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;

/***
 * 143. Reorder List
 * You are given the head of a singly linked-list. The list can be represented as:
 *   L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *   L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * Example 1:
 *   Input: head = [1,2,3,4]
 *   Output: [1,4,2,3]
 * Example 2:
 *   Input: head = [1,2,3,4,5]
 *   Output: [1,5,2,4,3]
 * Constraints:
 *   The number of nodes in the list is in the range [1, 5 * 10^4].
 *   1 <= Node.val <= 1000
 */
public class LC0143 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = new ListNode(0);
        pre.next = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            pre = pre.next;
        }

        pre.next = null;

        ListNode node = head;
        ListNode node1 = reverse(slow);

        while(true){
            ListNode temp = node.next;
            ListNode temp1 = node1.next;
            node.next = node1;
            if(temp == null) break;
            node1.next = temp;
            node = temp;
            node1 = temp1;
        }
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = head;
        ListNode cur = head.next;
        pre.next = null;

        while (cur != null) {
            ListNode node = cur.next;
            cur.next = pre;
            pre = cur;
            cur = node;
        }

        return pre;
    }
}
