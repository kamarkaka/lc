package com.kamarkaka.leetcode;

import com.kamarkaka.common.ListNode;
import com.kamarkaka.common.TreeNode;

/***
 * 109. Convert Sorted List to Binary Search Tree
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced
 * binary search tree.
 * Example 1:
 *   Input: head = [-10,-3,0,5,9]
 *   Output: [0,-3,9,-10,null,5]
 *   Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 * Example 2:
 *   Input: head = []
 *   Output: []
 * Constraints:
 *   The number of nodes in head is in the range [0, 2 * 10^4].
 *   -10^5 <= Node.val <= 10^5
 */
public class LC0109 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        ListNode slow = head, fast = head, prev = null;
        while (fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) break;
        }

        TreeNode root = new TreeNode(slow.val);
        if (prev != null) prev.next = null;
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
}
