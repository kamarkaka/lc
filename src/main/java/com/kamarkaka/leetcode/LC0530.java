package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 530. Minimum Absolute Difference in BST
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two
 * different nodes in the tree.
 * Example 1:
 *   Input: root = [4,2,6,1,3]
 *   Output: 1
 * Example 2:
 *   Input: root = [1,0,48,null,null,12,49]
 *   Output: 1
 * Constraints:
 *   The number of nodes in the tree is in the range [2, 10^4].
 *   0 <= Node.val <= 10^5
 * Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class LC0530 {
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;

        int leftDiff = getMinimumDifference(root.left);
        int rightDiff = getMinimumDifference(root.right);

        int leftDiff2 = Integer.MAX_VALUE;
        if (root.left != null) {
            leftDiff2 = Math.abs(root.val - getMax(root.left));
        }

        int rightDiff2 = Integer.MAX_VALUE;
        if (root.right != null) {
            rightDiff2 = Math.abs(root.val - getMin(root.right));
        }

        return Math.min(Math.min(leftDiff, rightDiff), Math.min(leftDiff2, rightDiff2));
    }

    private int getMax(TreeNode root) {
        if (root == null) return 0;

        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    private int getMin(TreeNode root) {
        if (root == null) return 0;

        TreeNode node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

}
