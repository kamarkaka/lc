package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/***
 * 272. Closest Binary Search Tree Value II
 * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are
 * closest to the target. You may return the answer in any order.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Example 1:
 *   Input: root = [4,2,5,1,3], target = 3.714286, k = 2
 *   Output: [4,3]
 * Example 2:
 *   Input: root = [1], target = 0.000000, k = 1
 *   Output: [1]
 * Constraints:
 *   The number of nodes in the tree is n.
 *   1 <= k <= n <= 10^4.
 *   0 <= Node.val <= 10^9
 *   -10^9 <= target <= 10^9
 * Follow up: Assume that the BST is balanced. Could you solve it in less than O(n) runtime (where n = total nodes)?
 */
public class LC0272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> deque = new LinkedList<>();
        helper(root, target, deque);

        while (deque.size() > k) {
            if (Math.abs(target - deque.peekFirst()) > Math.abs(target - deque.peekLast())) {
                deque.pollFirst();
            } else {
                deque.pollLast();
            }
        }
        return (List<Integer>) deque;
    }

    private void helper(TreeNode root, double target, Deque<Integer> deque) {
        if (root == null) return;

        helper(root.left, target, deque);
        deque.add(root.val);
        helper(root.right, target, deque);
    }
}
