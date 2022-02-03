package com.kamarkaka;

import com.kamarkaka.common.ListNode;
import com.kamarkaka.common.Utilities;

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
